package de.dsheng.linturtle.model.rules.common.element;

import org.omg.spec.bpmn._20100524.model.TGateway;

import de.dsheng.linturtle.model.BaseRule;
import de.dsheng.linturtle.model.annotation.Rule;
import de.dsheng.linturtle.model.entity.Element;
import de.dsheng.linturtle.model.entity.Severity;
import de.dsheng.linturtle.utils.RuleCheckUtils;

@Rule(severity = Severity.MUST, targetType = Element.GATEWAY, description = "Provide name for incoming gateway")
public class IncomingGatewayNameNotNullOrEmptyRule extends BaseRule {

    @Override
    public boolean check(Object element) {
        TGateway gateway = (TGateway) element;
        if(gateway.getIncoming().size() < gateway.getOutgoing().size()) {        
            return RuleCheckUtils.notNullOrEmpty(gateway.getName());
        }
        return true;
    }
}