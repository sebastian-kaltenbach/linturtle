package de.dsheng.controller;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Set;
import java.util.logging.Logger;

import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.project.MavenProject;
import org.reflections.Reflections;

import de.dsheng.model.RuleSet;
import de.dsheng.model.annotation.Rule;
import de.dsheng.model.rules.BaseRule;
import lombok.Getter;

public class RuleController {

    private Log log;
    private final String BASIC_RULE_PACKAGE = "com.ibm.model.rules.common";

    @Getter
    private RuleSet ruleSet;

    @Getter 
    private RuleSet customRuleSet;

    @Getter
    private RuleSet skippedRules;

    public RuleController(Log log) {
        this.ruleSet = new RuleSet();
        this.skippedRules = new RuleSet();
        this.customRuleSet = new RuleSet();
        this.log = log;
    }

    public RuleController prepare(MavenProject project, Set<String> skipRules, String customRulePackage) {
        handleIndexer();
        setupSkippetRuleSet(skipRules);
        gatherCustomRules(project, customRulePackage);
        return this;
    }

    private void handleIndexer() {
        log.info("Lookup rules in package " + BASIC_RULE_PACKAGE);
        Reflections reflections = new Reflections(BASIC_RULE_PACKAGE);
        Set<Class<?>> ruleClasses = reflections.getTypesAnnotatedWith(Rule.class);
        ruleClasses.forEach(ruleClass -> {
            try {
                ruleSet.addRule((BaseRule) ruleClass.getDeclaredConstructor().newInstance());
                log.debug("Found Rule: " + ruleClass.getSimpleName() + "  |  Added to RuleSet");
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException | SecurityException | NoSuchMethodException e) {
                log.warn(ruleClass.getSimpleName() + " could not be added to common rule set | " + e.getMessage());
            }    
            log.info("Rule: " + ruleClass.getSimpleName() + " added!");       
        });
    }

    private void setupSkippetRuleSet(Set<String> skipRules) {
        skippedRules.setRules(this.ruleSet.getRules().stream().filter(rule -> skipRules.contains(rule.getClass().getSimpleName())).toList());
        var deltaRules = ruleSet.getRules();
        deltaRules.removeAll(skippedRules.getRules());
        ruleSet.setRules(deltaRules);
    }

    private void gatherCustomRules(MavenProject project, String customRulePackage) {
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
                        Class<?> ruleClass;
                        try {
                            ruleClass = loader.loadClass(customRulePackage + "." + classFile.getName().substring(0, classFile.getName().lastIndexOf(".")));
                            this.customRuleSet.addRule((BaseRule) ruleClass.getDeclaredConstructor().newInstance());
                        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }        
                    });
                } catch (MalformedURLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            });        
        } catch (DependencyResolutionRequiredException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
