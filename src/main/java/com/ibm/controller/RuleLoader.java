package com.ibm.controller;

import java.io.IOException;
import java.util.List;

import org.apache.maven.plugin.logging.Log;
import org.camunda.bpm.model.dmn.instance.Rule;
import org.jboss.jandex.AnnotationInstance;
import org.jboss.jandex.DotName;
import org.jboss.jandex.Index;
import org.jboss.jandex.Indexer;

import com.ibm.model.RuleSet;

public class RuleLoader {

    private Log logger;

    public RuleLoader(Log logger) {
        this.logger = logger;
    }

    public RuleSet generateBasicRuleSet() throws IOException {
        logger.info("In Generator");
        RuleSet ruleSet = new RuleSet();

        Indexer indexer = new Indexer();
        indexer.indexClass(Rule.class);
        Index index = indexer.complete();


        DotName rule = DotName.createSimple("com.ibm.model.Rule");
        List<AnnotationInstance> annotations = index.getAnnotations(rule);
        
        annotations.forEach(e -> logger.info(e.target().kind().name()));
        logger.info("After generation");

        return ruleSet;
    }
}