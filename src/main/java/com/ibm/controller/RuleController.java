package com.ibm.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import java.util.logging.Logger;
import org.reflections.Reflections;
import com.ibm.model.RuleSet;
import com.ibm.model.annotation.Rule;
import com.ibm.model.rules.BaseRule;

public class RuleController extends Controller {

    private static final Logger LOG = Logger.getLogger("RuleSetController.class");
    
    private RuleSet ruleSet;

    public RuleSet getRuleSet() {
        return this.ruleSet;
    }

    public RuleController() {
        this.ruleSet = new RuleSet();
    }

    @Override
    public Controller prepare() {
        handleIndexer();
        return this;
    }

    private void handleIndexer() {
        Reflections reflections = new Reflections("com.ibm.model.rules.common");
        Set<Class<?>> ruleClasses = reflections.getTypesAnnotatedWith(Rule.class);

        ruleClasses.forEach(ruleClass -> {
            try {
                ruleSet.getRules().add((BaseRule) ruleClass.getDeclaredConstructor().newInstance());
                LOG.info("Found Rule: " + ruleClass.getSimpleName() + "  |  Added to RuleSet");
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException | SecurityException | NoSuchMethodException e) {
                LOG.info(e.getMessage());
            }           
        });
    }
}
