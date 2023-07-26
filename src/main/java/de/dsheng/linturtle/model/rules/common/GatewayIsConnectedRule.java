package de.dsheng.linturtle.model.rules.common;

import org.omg.spec.bpmn._20100524.model.TGateway;

import de.dsheng.linturtle.model.RuleResult;
import de.dsheng.linturtle.model.annotation.Rule;
import de.dsheng.linturtle.model.entity.Element;
import de.dsheng.linturtle.model.entity.Severity;
import de.dsheng.linturtle.model.rules.BaseRule;
import de.dsheng.linturtle.utils.RuleCheckUtils;

@Rule(description = "Checks, if Gateway is connected to the process", severity = Severity.MUST, targetType = Element.GATEWAY)
public class GatewayIsConnectedRule extends BaseRule{

    @Override
    public RuleResult check(Object OUT) {
        TGateway gateway = (TGateway) OUT;
        return new RuleResult(RuleCheckUtils.hasConnection(gateway.getIncoming()) && RuleCheckUtils.hasConnection(gateway.getOutgoing()), 
            gateway.getId());
    }    
}
