package de.dsheng.linturtle.model.rules.common;

import org.omg.spec.bpmn._20100524.model.TGateway;

import de.dsheng.linturtle.model.RuleResult;
import de.dsheng.linturtle.model.annotation.Rule;
import de.dsheng.linturtle.model.entity.Element;
import de.dsheng.linturtle.model.entity.Severity;
import de.dsheng.linturtle.model.rules.BaseRule;

@Rule(severity = Severity.MUST, targetType = Element.GATEWAY, description = "Checks, if Gateways have a non-null name")
public class IncomingGatewayNameNonNullOrEmptyRule extends BaseRule {

    @Override
    public RuleResult check(Object OUT) {
        TGateway gateway = (TGateway) OUT;
        RuleResult result = new RuleResult(true, gateway.getId());
        if(gateway.getIncoming().size() < gateway.getOutgoing().size()) {        
            return result.valid(gateway.getName() == null || gateway.getName() == "" ?  false :  true);
        }
        return result;
    }
}