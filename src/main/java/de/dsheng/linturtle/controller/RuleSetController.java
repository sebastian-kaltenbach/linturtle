package de.dsheng.linturtle.controller;

import org.apache.maven.plugin.logging.Log;
import org.omg.spec.bpmn._20100524.model.TProcess;

import de.dsheng.linturtle.model.RuleResult;
import de.dsheng.linturtle.model.RuleSet;
import de.dsheng.linturtle.model.Violation;
import de.dsheng.linturtle.model.ViolationSet;
import de.dsheng.linturtle.model.annotation.Rule;
import lombok.Getter;

public class RuleSetController {

    private Log log;
    private RuleSet ruleSet;
    private TProcess bpmnProcess;

    @Getter
    private ViolationSet violationSet;


    public RuleSetController(RuleSet ruleSet, TProcess bpmnProcess, Log log) {
        this.log = log;
        this.ruleSet = ruleSet;
        this.bpmnProcess = bpmnProcess;
        violationSet = new ViolationSet();
        log.debug("RuleSetController initialized.");
    }

    public RuleSetController execute() {
        log.debug("RuleSetController executed");
        ruleSet.getRules().forEach(rule -> {
            Rule ruleAnnotation = rule.getClass().getAnnotation(Rule.class);

            try {
                bpmnProcess.getFlowElement().stream()
                    .filter(flowElement -> flowElement.getName().getLocalPart().toLowerCase()
                        .equals(ruleAnnotation.targetType().name().toLowerCase())).forEach(target -> {
                            RuleResult result = rule.check(target.getValue());
                            if(!result.isValid()) {
                                violationSet.getViolations().add(new Violation(rule, bpmnProcess, result.getTargetIdentifier()));
                            }
                        });
            } catch(Exception e) {
                log.error(e.getMessage(), e);
            }
        });
        return this;
    }
}
