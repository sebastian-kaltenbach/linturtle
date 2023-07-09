package com.ibm.model.rules.common;

import org.camunda.bpm.model.bpmn.instance.Gateway;

import com.ibm.model.RuleResult;
import com.ibm.model.annotation.Rule;
import com.ibm.model.entity.Element;
import com.ibm.model.entity.Severity;
import com.ibm.model.rules.BaseRule;

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