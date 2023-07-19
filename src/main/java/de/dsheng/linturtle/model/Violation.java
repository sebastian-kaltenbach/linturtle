package de.dsheng.linturtle.model;

import org.omg.spec.bpmn._20100524.model.TProcess;

import de.dsheng.linturtle.model.rules.BaseRule;
import lombok.Data;

@Data
public class Violation {
    private BaseRule rule;
    private TProcess bpmnProcess;
    private String targetId;

    public Violation(BaseRule rule, TProcess bpmnProcess, String targetId) {
        this.rule = rule;
        this.bpmnProcess = bpmnProcess;
        this.targetId = targetId;
    }

    @Override
    public String toString() {
        return String.format("Violation(rule=%s, bpmnProcess=%s, targetID=%s)", rule.getClass().getSimpleName(), bpmnProcess.getName(), targetId);
    }
}
