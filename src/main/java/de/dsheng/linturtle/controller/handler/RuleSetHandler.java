package de.dsheng.linturtle.controller.handler;

import java.util.Map;

import org.apache.maven.plugin.logging.Log;
import org.omg.spec.bpmn._20100524.model.TProcess;

import de.dsheng.linturtle.model.BaseRule;
import de.dsheng.linturtle.model.ElementRule;
import de.dsheng.linturtle.model.GlobalRule;
import de.dsheng.linturtle.model.RuleSet;
import de.dsheng.linturtle.model.Violation;
import de.dsheng.linturtle.model.ViolationSet;
import de.dsheng.linturtle.model.annotation.Rule;
import de.dsheng.linturtle.utils.ProcessUtils;
import lombok.Getter;

public class RuleSetHandler {

    private Log log;
    private RuleSet ruleSet;
    private TProcess bpmnProcess;

    @Getter
    private ViolationSet violationSet;


    public RuleSetHandler(RuleSet ruleSet, TProcess bpmnProcess, Log log) {
        this.log = log;
        this.ruleSet = ruleSet;
        this.bpmnProcess = bpmnProcess;
        violationSet = new ViolationSet();
        log.debug("RuleSetController initialized.");
    }

    public RuleSetHandler execute() {
        log.debug("RuleSetController executed");

        ruleSet.getRules().forEach(rule -> {
            Rule ruleAnnotation = rule.getClass().getAnnotation(Rule.class);
            if(rule instanceof GlobalRule) {
                var globalRule = (GlobalRule) rule;
                this.handleRuleResult(globalRule, bpmnProcess, globalRule.check(bpmnProcess));
            } else {
                var elementRule = (ElementRule) rule;
                ProcessUtils.getProcessElementsByElement(bpmnProcess, ruleAnnotation.targetType()).forEach(flowElement -> {
                    this.handleRuleResult(elementRule, bpmnProcess, Map.of(flowElement.getValue().getId(), elementRule.check(flowElement.getValue())));    
                });
            }          
        });
        return this;
    }

    private void handleRuleResult(BaseRule rule, TProcess process, Map<String, Boolean> result) {
        result.entrySet().stream().forEach((entry) -> {
            if(!entry.getValue().booleanValue()) {
                violationSet.getViolations().add(new Violation(rule, process, entry.getKey()));
            }
        });
    }
}
