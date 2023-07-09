package com.ibm.model;

import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import com.ibm.model.rules.BaseRule;
import lombok.Data;

@Data
public class Violation {
    private BaseRule rule;
    private BpmnModelInstance bpmnModelInstance;
    private String targetId;

    public Violation(BaseRule rule, BpmnModelInstance bpmnModelInstance, String targetId) {
        this.rule = rule;
        this.bpmnModelInstance = bpmnModelInstance;
        this.targetId = targetId;
    }

    @Override
    public String toString() {
        return String.format("Violation(rule=%s, bpmnModelInstance=%s, targetID=%s)", rule.getClass().getSimpleName(), bpmnModelInstance.getDefinitions().getAttributeValue("id"), targetId);
    }
}
