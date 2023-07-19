package de.dsheng.linturtle.model.rules.common;

import org.omg.spec.bpmn._20100524.model.TGateway;

import de.dsheng.linturtle.model.RuleResult;
import de.dsheng.linturtle.model.annotation.Rule;
import de.dsheng.linturtle.model.entity.Element;
import de.dsheng.linturtle.model.entity.Severity;
import de.dsheng.linturtle.model.rules.BaseRule;

@Rule(severity = Severity.MUST, targetType = Element.GATEWAY, description = "Checks, if Gateways have a non-null name")
public class GatewayNameNonNullRule extends BaseRule {

    public GatewayNameNonNullRule() {
    }

    @Override
    public RuleResult check(Object OUT) {
        TGateway targetType = (TGateway) OUT;
        return new RuleResult(targetType.getName() == null || targetType.getName() == "" ?  false :  true, targetType.getId());
    }
}