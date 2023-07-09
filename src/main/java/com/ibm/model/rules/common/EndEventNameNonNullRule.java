package com.ibm.model.rules.common;

import org.camunda.bpm.model.bpmn.instance.EndEvent;
import org.camunda.bpm.model.bpmn.instance.StartEvent;

import com.ibm.model.RuleResult;
import com.ibm.model.annotation.Rule;
import com.ibm.model.entity.Element;
import com.ibm.model.entity.Severity;
import com.ibm.model.rules.BaseRule;

@Rule(severity = Severity.MUST, targetType = Element.ENDEVENT)
public class EndEventNameNonNullRule extends BaseRule {

    public EndEventNameNonNullRule() {
    }

    @Override
    public RuleResult check(Object OUT) {
        EndEvent targetType = (EndEvent) OUT;
        return new RuleResult(targetType.getName() == null || targetType.getName() == "" ?  false :  true, targetType.getId());
    }
}