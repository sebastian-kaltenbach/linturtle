package de.dsheng.linturtle.model.rules.common;

import org.omg.spec.bpmn._20100524.model.TStartEvent;

import de.dsheng.linturtle.model.RuleResult;
import de.dsheng.linturtle.model.annotation.Rule;
import de.dsheng.linturtle.model.entity.Element;
import de.dsheng.linturtle.model.entity.Severity;
import de.dsheng.linturtle.model.rules.BaseRule;
import de.dsheng.linturtle.utils.RuleCheckUtils;

@Rule(description = "Checks, if Startevent is connected to the process", severity = Severity.MUST, targetType = Element.STARTEVENT)
public class StartEventIsConnectedRule extends BaseRule{

    @Override
    public RuleResult check(Object OUT) {
        TStartEvent startEvent = (TStartEvent) OUT;
        return new RuleResult(RuleCheckUtils.hasConnection(startEvent.getOutgoing()), startEvent.getId());
    }
    
}
