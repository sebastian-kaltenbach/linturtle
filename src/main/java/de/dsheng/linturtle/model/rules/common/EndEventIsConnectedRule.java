package de.dsheng.linturtle.model.rules.common;

import org.omg.spec.bpmn._20100524.model.TEndEvent;

import de.dsheng.linturtle.model.RuleResult;
import de.dsheng.linturtle.model.annotation.Rule;
import de.dsheng.linturtle.model.entity.Element;
import de.dsheng.linturtle.model.entity.Severity;
import de.dsheng.linturtle.model.rules.BaseRule;
import de.dsheng.linturtle.utils.RuleCheckUtils;

@Rule(description = "Checks, if Endevent is connected to the process", severity = Severity.MUST, targetType = Element.ENDEVENT)
public class EndEventIsConnectedRule extends BaseRule{

    @Override
    public RuleResult check(Object OUT) {
        TEndEvent startEvent = (TEndEvent) OUT;
        return new RuleResult(RuleCheckUtils.hasConnection(startEvent.getIncoming()), startEvent.getId());
    }    
}
