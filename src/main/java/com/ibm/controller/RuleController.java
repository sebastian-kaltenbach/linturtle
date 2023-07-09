package com.ibm.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import java.util.logging.Logger;

import org.apache.maven.plugin.logging.Log;
import org.reflections.Reflections;
import com.ibm.model.RuleSet;
import com.ibm.model.annotation.Rule;
import com.ibm.model.rules.BaseRule;

public class RuleController {

    private static final Logger LOG = Logger.getLogger("RuleSetController.class");
    private Log log;
    private RuleSet ruleSet;

    public RuleSet getRuleSet() {
        return this.ruleSet;
    }

    public RuleController(Log log) {
        this.ruleSet = new RuleSet();
        this.log = log;
    }

    public RuleController prepare() {
        handleIndexer();
        return this;
    }

    private void handleIndexer() {
        Reflections reflections = new Reflections("com.ibm.model.rules.common");
        Set<Class<?>> ruleClasses = reflections.getTypesAnnotatedWith(Rule.class);
        ruleClasses.forEach(ruleClass -> {
            try {
                ruleSet.getRules().add((BaseRule) ruleClass.getDeclaredConstructor().newInstance());
                log.info("Found Rule: " + ruleClass.getSimpleName() + "  |  Added to RuleSet");
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException | SecurityException | NoSuchMethodException e) {
                log.warn(ruleClass.getClass().getSimpleName() + " could not be added to common rule set | " + e.getMessage());
            }    
            log.info("Basic RuleSet: " + ruleSet.getRules().size() + " added!");       
        });
    }
}
