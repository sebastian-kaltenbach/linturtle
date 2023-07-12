package de.dsheng.model.rules.common;

import org.camunda.bpm.model.bpmn.instance.StartEvent;

import de.dsheng.model.RuleResult;
import de.dsheng.model.annotation.Rule;
import de.dsheng.model.entity.Element;
import de.dsheng.model.entity.Severity;
import de.dsheng.model.rules.BaseRule;

@Rule(severity = Severity.MUST, targetType = Element.STARTEVENT, description = "Checks, if Start Events have a non-null name")
public class StartEventNameNonNullRule extends BaseRule {

    public StartEventNameNonNullRule() {
    }

    @Override
    public RuleResult check(Object OUT) {
        StartEvent targetType = (StartEvent) OUT;
        return new RuleResult(targetType.getName() == null || targetType.getName() == "" ?  false :  true, targetType.getId());
    }
}