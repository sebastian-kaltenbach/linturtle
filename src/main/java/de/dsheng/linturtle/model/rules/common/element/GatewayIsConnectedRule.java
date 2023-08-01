package de.dsheng.linturtle.model.rules.common.element;

import org.omg.spec.bpmn._20100524.model.TGateway;

import de.dsheng.linturtle.model.BaseRule;
import de.dsheng.linturtle.model.annotation.Rule;
import de.dsheng.linturtle.model.entity.Element;
import de.dsheng.linturtle.model.entity.Severity;
import de.dsheng.linturtle.utils.RuleCheckUtils;

@Rule(description = "Provide connection for gateway", severity = Severity.MUST, targetType = Element.GATEWAY)
public class GatewayIsConnectedRule extends BaseRule {

    @Override
    public boolean check(Object element) {
        TGateway gateway = (TGateway) element;
        return RuleCheckUtils.hasConnection(gateway.getIncoming()) && RuleCheckUtils.hasConnection(gateway.getOutgoing());
    }    
}
