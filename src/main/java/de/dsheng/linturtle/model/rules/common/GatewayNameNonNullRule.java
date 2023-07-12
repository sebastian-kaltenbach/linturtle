package de.dsheng.linturtle.model.rules.common;

import org.camunda.bpm.model.bpmn.instance.Gateway;

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
        Gateway targetType = (Gateway) OUT;
        return new RuleResult(targetType.getName() == null || targetType.getName() == "" ?  false :  true, targetType.getId());
    }
}