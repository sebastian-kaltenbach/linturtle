package de.dsheng.model.rules.common;

import org.camunda.bpm.model.bpmn.instance.Gateway;

import de.dsheng.model.RuleResult;
import de.dsheng.model.annotation.Rule;
import de.dsheng.model.entity.Element;
import de.dsheng.model.entity.Severity;
import de.dsheng.model.rules.BaseRule;

@Rule(severity = Severity.MUST, targetType = Element.GATEWAY)
public class GatewayNameNonNullRule extends BaseRule {

    public GatewayNameNonNullRule() {
    }

    @Override
    public RuleResult check(Object OUT) {
        Gateway targetType = (Gateway) OUT;
        return new RuleResult(targetType.getName() == null || targetType.getName() == "" ?  false :  true, targetType.getId());
    }
}