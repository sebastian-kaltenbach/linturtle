package de.dsheng.model.rules.common;

import org.camunda.bpm.model.bpmn.instance.EndEvent;

import de.dsheng.model.RuleResult;
import de.dsheng.model.annotation.Rule;
import de.dsheng.model.entity.Element;
import de.dsheng.model.entity.Severity;
import de.dsheng.model.rules.BaseRule;

@Rule(severity = Severity.MUST, targetType = Element.ENDEVENT, description = "Checks, if End Events have a non-null name")
public class EndEventNameNonNullRule extends BaseRule {

    public EndEventNameNonNullRule() {
    }

    @Override
    public RuleResult check(Object OUT) {
        EndEvent targetType = (EndEvent) OUT;
        return new RuleResult(targetType.getName() == null || targetType.getName() == "" ?  false :  true, targetType.getId());
    }
}