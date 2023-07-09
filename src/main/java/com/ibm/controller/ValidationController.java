package com.ibm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.maven.plugin.logging.Log;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import com.ibm.model.Violation;
import lombok.Getter;

public class ValidationController {

    private Log log;
    private BPMNController bpmnController;
    private RuleController ruleController;

    @Getter
    private Map<BpmnModelInstance, List<Violation>> violationSet;

    public ValidationController(String source, Log log) {
        this.log = log;
        this.bpmnController = (BPMNController) new BPMNController(source).prepare();
        this.ruleController = (RuleController) new RuleController().prepare();
        this.violationSet = new HashMap<>();
    }

    public void execute() {
        bpmnController.gBpmnModelInstances().forEach(model -> {
            RuleSetController controller = new RuleSetController(ruleController.getRuleSet(), model).execute();
            this.violationSet.put(model, controller.getViolations());
        });
    }
}
