package de.dsheng.linturtle.model.rules.common;

import org.omg.spec.bpmn._20100524.model.TStartEvent;

import de.dsheng.linturtle.model.RuleResult;
import de.dsheng.linturtle.model.annotation.Rule;
import de.dsheng.linturtle.model.entity.Element;
import de.dsheng.linturtle.model.entity.Severity;
import de.dsheng.linturtle.model.rules.BaseRule;

@Rule(severity = Severity.MUST, targetType = Element.STARTEVENT, description = "Checks, if Start Events have a non-null name", id = 2)
public class StartEventNameNonNullRule extends BaseRule {

    public StartEventNameNonNullRule() {
    }

    @Override
    public RuleResult check(Object OUT) {
        TStartEvent startEvent = (TStartEvent) OUT;
        return new RuleResult(startEvent.getName() == null || startEvent.getName() == "" ?  false :  true, startEvent.getId());
    }
}