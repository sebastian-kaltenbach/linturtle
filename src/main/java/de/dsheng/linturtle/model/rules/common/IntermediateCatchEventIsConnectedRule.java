package de.dsheng.linturtle.model.rules.common;

import org.omg.spec.bpmn._20100524.model.TIntermediateCatchEvent;

import de.dsheng.linturtle.model.RuleResult;
import de.dsheng.linturtle.model.annotation.Rule;
import de.dsheng.linturtle.model.entity.Element;
import de.dsheng.linturtle.model.entity.Severity;
import de.dsheng.linturtle.model.rules.BaseRule;
import de.dsheng.linturtle.utils.RuleCheckUtils;

@Rule(description = "Checks, if Gateway is connected to the process", severity = Severity.MUST, targetType = Element.INTERMEDIATECATCHEVENT)
public class IntermediateCatchEventIsConnectedRule extends BaseRule{

    @Override
    public RuleResult check(Object OUT) {
        TIntermediateCatchEvent intermediateCatchEvent = (TIntermediateCatchEvent) OUT;
        return new RuleResult(RuleCheckUtils.hasConnection(intermediateCatchEvent.getIncoming()) && RuleCheckUtils.hasConnection(intermediateCatchEvent.getOutgoing()), 
            intermediateCatchEvent.getId());
    }    
}
