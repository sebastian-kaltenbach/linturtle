package com.ibm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.maven.plugin.logging.Log;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;

import com.ibm.model.ViolationSet;
import com.ibm.model.entity.Severity;
import com.typesafe.config.Config;

import lombok.Getter;

public class ValidationController {

    private Log log;
    private BPMNController bpmnController;
    private RuleController ruleController;
    private ReportController reportController;

    @Getter
    private Map<BpmnModelInstance, ViolationSet> violationSets;

    public ValidationController(Config config, String source, Set<String> skipRules, Log log) {
        this.log = log;
        this.bpmnController = new BPMNController(source, log).prepare();
        this.ruleController = new RuleController(log).prepare(skipRules);
        this.reportController = new ReportController(config, log);
        this.violationSets = new HashMap<>();
        log.debug("ValidationController initialized.");
    }

    public void execute() {
        log.debug("ValidationController executed.");
        bpmnController.gBpmnModelInstances().forEach(model -> {
            RuleSetController controller = new RuleSetController(ruleController.getRuleSet(), model, log).execute();
            this.violationSets.put(model, controller.getViolationSet());
        });
        reportController.printResultToConsole(ruleController.getRuleSet(), ruleController.getSkippedRules(), this.violationSets);
    }

    public void executeReportController() {
        reportController.execute(this.violationSets);
    }

    public boolean checkViolationsForSeverity(List<Severity> failOn) {
        for(var entry : this.violationSets.entrySet()) {   
            if(!entry.getValue().getViolationsBySeverity(failOn).isEmpty()) return true;
        }
        return false;
    }
}
