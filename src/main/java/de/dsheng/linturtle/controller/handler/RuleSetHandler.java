package de.dsheng.linturtle.controller.handler;

import java.util.Map;

import org.apache.maven.plugin.logging.Log;

import de.dsheng.linturtle.model.BaseRule;
import de.dsheng.linturtle.model.BpmnProvider;
import de.dsheng.linturtle.model.RuleSet;
import de.dsheng.linturtle.model.Violation;
import de.dsheng.linturtle.model.ViolationSet;
import de.dsheng.linturtle.model.annotation.Rule;
import de.dsheng.linturtle.utils.ProcessUtils;
import lombok.Getter;

public class RuleSetHandler {

    private Log log;
    private RuleSet ruleSet;
    private BpmnProvider provider;

    @Getter
    private ViolationSet violationSet;


    public RuleSetHandler(RuleSet ruleSet, BpmnProvider provider, Log log) {
        this.log = log;
        this.ruleSet = ruleSet;
        this.provider = provider;
        violationSet = new ViolationSet();
        log.debug("RuleSetController initialized.");
    }

    public RuleSetHandler execute() {
        log.debug("RuleSetController executed");

        ruleSet.getRules().forEach(rule -> {
            Rule ruleAnnotation = rule.getClass().getAnnotation(Rule.class);

            var elementRule = (BaseRule) rule;
            ProcessUtils.getProcessElementsByElement(provider.getProcess(), ruleAnnotation.targetType()).forEach(flowElement -> {
                this.handleRuleResult(elementRule, provider.getFileName(), Map.of(flowElement.getValue().getId(), elementRule.check(flowElement.getValue())));    
            });
                      
        });
        return this;
    }

    private void handleRuleResult(BaseRule rule, String bpmnProcessFileName, Map<String, Boolean> result) {
        result.entrySet().stream().forEach((entry) -> {
            if(!entry.getValue().booleanValue()) {
                violationSet.getViolations().add(new Violation(rule, bpmnProcessFileName, entry.getKey()));
            }
        });
    }
}
