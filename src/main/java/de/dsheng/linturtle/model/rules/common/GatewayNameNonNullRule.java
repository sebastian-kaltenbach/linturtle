package de.dsheng.linturtle.model.rules.common;

import org.omg.spec.bpmn._20100524.model.TGateway;

import de.dsheng.linturtle.model.RuleResult;
import de.dsheng.linturtle.model.annotation.Rule;
import de.dsheng.linturtle.model.entity.Element;
import de.dsheng.linturtle.model.entity.Severity;
import de.dsheng.linturtle.model.rules.BaseRule;

@Rule(severity = Severity.MUST, targetType = Element.GATEWAY, description = "Checks, if Gateways have a non-null name", id = 0)
public class GatewayNameNonNullRule extends BaseRule {

    public GatewayNameNonNullRule() {
    }

    @Override
    public RuleResult check(Object OUT) {
        TGateway gateway = (TGateway) OUT;
        return new RuleResult(gateway.getName() == null || gateway.getName() == "" ?  false :  true, gateway.getId());
    }
}