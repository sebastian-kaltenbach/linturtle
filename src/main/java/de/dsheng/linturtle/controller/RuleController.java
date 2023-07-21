package de.dsheng.linturtle.controller;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Set;

import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.project.MavenProject;
import org.reflections.Reflections;

import de.dsheng.linturtle.model.RuleSet;
import de.dsheng.linturtle.model.annotation.Rule;
import de.dsheng.linturtle.utils.RuleMapper;
import lombok.Getter;

public class RuleController {

    private Log log;
    private final String BASIC_RULE_PACKAGE = "de.dsheng.linturtle.model.rules.common";

    @Getter
    private RuleSet commonRuleSet;

    @Getter 
    private RuleSet customRuleSet;

    @Getter
    private RuleSet skippedRuleSet;

    @Getter
    private RuleSet activeRuleSet;

    public RuleController(Log log) {
        this.commonRuleSet = new RuleSet();
        this.skippedRuleSet = new RuleSet();
        this.customRuleSet = new RuleSet();
        this.activeRuleSet = new RuleSet();
        this.log = log;
    }

    public RuleController prepare(MavenProject project, Set<String> skipRules, String customRulePackage) {
        loadCommonRulesToRuleSet();
        loadSkippedRulesToRuleSet(skipRules);
        loadCustomRulesToRuleSet(project, customRulePackage);
        prepareActiveRuleSet();

        printFoundRules();
        return this;
    }

    private void loadCommonRulesToRuleSet() {
        Reflections reflections = new Reflections(BASIC_RULE_PACKAGE);
        Set<Class<?>> ruleClasses = reflections.getTypesAnnotatedWith(Rule.class);
        ruleClasses.forEach(ruleClass -> this.commonRuleSet.addRule(RuleMapper.transformClassToRule(ruleClass)));
    }

    private void loadSkippedRulesToRuleSet(Set<String> skipRules) {
        skippedRuleSet.setRules(this.commonRuleSet.getRules().stream().filter(rule -> skipRules.contains(rule.getClass().getSimpleName())).toList());
    }

    private void loadCustomRulesToRuleSet(MavenProject project, String customRulePackage) {
        try {
            project.getCompileClasspathElements().forEach(e -> {
                URL url;
                try {
                    File classPathDir = new File(e);
                    url = classPathDir.toURI().toURL();
                    URL[] urls = new URL[]{url};
                    ClassLoader loader = new URLClassLoader(urls, this.getClass().getClassLoader());

                    File classFileDir = Paths.get(classPathDir.toString(), customRulePackage.replace(".", "/")).toFile();

                    Arrays.stream(classFileDir.listFiles()).forEach(classFile -> {
                        try {
                            Class<?> clazz = loader.loadClass(customRulePackage + "." + classFile.getName().substring(0, classFile.getName().lastIndexOf(".")));
                            this.customRuleSet.addRule(RuleMapper.transformClassToRule(clazz));

                        } catch (ClassNotFoundException e1) {
                            log.error(e1.getMessage(), e1);
                        }     
                    });
                } catch (MalformedURLException e1) {
                    log.error(e1.getMessage(), e1);
                }
            });        
        } catch (DependencyResolutionRequiredException e) {
            log.error(e.getMessage(), e);
        }
    }

    private void prepareActiveRuleSet() {
        activeRuleSet.addRules(this.commonRuleSet.getRules());
        if(!this.customRuleSet.getRules().isEmpty()) activeRuleSet.addRules(this.customRuleSet.getRules());
        var tmpRules = activeRuleSet.getRules();
        tmpRules.removeAll(this.skippedRuleSet.getRules());
        activeRuleSet.setRules(tmpRules);
    }

    private void printFoundRules() {
        String header = "";
        if(!this.activeRuleSet.getRules().isEmpty()) {
            header = "Active Rules (" + this.activeRuleSet.getRules().size() + ")";
            printRulesByRuleSet(header, this.activeRuleSet);
        }
        if(!this.skippedRuleSet.getRules().isEmpty()) {
            header = "Skipped Rules (" + this.skippedRuleSet.getRules().size() + ")";
            printRulesByRuleSet(header, this.skippedRuleSet);
        }
    }

    private void printRulesByRuleSet(String header, RuleSet ruleSet) {
        StringBuilder sb = new StringBuilder();
        log.info("");
        log.info(header);
        
        for(int i =0; i < header.length(); i++) sb.append("-");
        log.info(sb.toString());
        
        ruleSet.getRules().forEach(rule -> {
            Rule ruleAnnotation = rule.getClass().getAnnotation(Rule.class);
            log.info("\t- " + rule.getClass().getSimpleName() + " | " + ruleAnnotation.severity().toString() +
            " | " + ruleAnnotation.targetType().toString() + " | " + ruleAnnotation.description());
        });
    }
}
