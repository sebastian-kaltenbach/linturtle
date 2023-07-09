package com.ibm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.maven.plugin.logging.Log;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import com.ibm.model.Violation;
import com.typesafe.config.Config;

import lombok.Getter;

public class ValidationController {

    private Log log;
    private BPMNController bpmnController;
    private RuleController ruleController;
    private ReportController reportController;

    @Getter
    private Map<BpmnModelInstance, List<Violation>> violationSet;

    public ValidationController(Config config, String source, Log log) {
        this.log = log;
        this.bpmnController = new BPMNController(source, log).prepare();
        this.ruleController = new RuleController(log).prepare();
        this.reportController = new ReportController(config, log);
        this.violationSet = new HashMap<>();
        log.debug("ValidationController initialized.");
    }

    public void execute() {
        log.debug("ValidationController executed.");
        bpmnController.gBpmnModelInstances().forEach(model -> {
            RuleSetController controller = new RuleSetController(ruleController.getRuleSet(), model, log).execute();
            this.violationSet.put(model, controller.getViolations());
        });
        reportController.printResultToConsole(ruleController.getRuleSet(), ruleController.getSkippedRules());
    }

    public void executeReportController() {
        reportController.execute(this.violationSet);
    }
}
