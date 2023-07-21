package de.dsheng.linturtle.model.rules.common;

import org.omg.spec.bpmn._20100524.model.TIntermediateThrowEvent;
import de.dsheng.linturtle.model.RuleResult;
import de.dsheng.linturtle.model.annotation.Rule;
import de.dsheng.linturtle.model.entity.Element;
import de.dsheng.linturtle.model.entity.Severity;
import de.dsheng.linturtle.model.rules.BaseRule;

@Rule(description = "Checks if intermediate throw event name is not null or empty", id = 0, severity = Severity.MUST, targetType = Element.INTERMEDIATETHROWEVENT)
public class IntermediateThrowEventNameNonNullOrEmpty extends BaseRule {

    @Override
    public RuleResult check(Object OUT) {
        TIntermediateThrowEvent intermediateThrowEvent = (TIntermediateThrowEvent) OUT;
        return new RuleResult(intermediateThrowEvent.getName() == null || intermediateThrowEvent.getName() == "" ?  false :  true, intermediateThrowEvent.getId());
    }
    
}
