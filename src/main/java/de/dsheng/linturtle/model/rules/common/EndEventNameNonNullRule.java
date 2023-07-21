package de.dsheng.linturtle.model.rules.common;

import org.omg.spec.bpmn._20100524.model.TEndEvent;

import de.dsheng.linturtle.model.RuleResult;
import de.dsheng.linturtle.model.annotation.Rule;
import de.dsheng.linturtle.model.entity.Element;
import de.dsheng.linturtle.model.entity.Severity;
import de.dsheng.linturtle.model.rules.BaseRule;

@Rule(severity = Severity.MUST, targetType = Element.ENDEVENT, description = "Checks, if End Events have a non-null name", id = 3)
public class EndEventNameNonNullRule extends BaseRule {

    public EndEventNameNonNullRule() {
    }

    @Override
    public RuleResult check(Object OUT) {
        TEndEvent endEvent = (TEndEvent) OUT;
        return new RuleResult(endEvent.getName() == null || endEvent.getName() == "" ?  false :  true, endEvent.getId());
    }
}