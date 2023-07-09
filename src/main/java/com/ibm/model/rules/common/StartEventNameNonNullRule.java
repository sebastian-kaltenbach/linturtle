package com.ibm.model.rules.common;

import java.util.logging.Logger;

import org.camunda.bpm.model.bpmn.instance.StartEvent;
import org.camunda.bpm.model.bpmn.instance.Task;

import com.ibm.model.RuleResult;
import com.ibm.model.annotation.Rule;
import com.ibm.model.entity.Element;
import com.ibm.model.entity.Severity;
import com.ibm.model.rules.BaseRule;

@Rule(severity = Severity.MUST, targetType = Element.STARTEVENT)
public class StartEventNameNonNullRule extends BaseRule {

    public StartEventNameNonNullRule() {
    }

    @Override
    public RuleResult check(Object OUT) {
        StartEvent targetType = (StartEvent) OUT;
        return new RuleResult(targetType.getName() == null || targetType.getName() == "" ?  false :  true, targetType.getId());
    }
}