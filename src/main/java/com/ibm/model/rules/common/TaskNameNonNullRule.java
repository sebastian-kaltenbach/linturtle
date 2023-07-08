package com.ibm.model.rules.common;

import java.util.logging.Logger;

import org.camunda.bpm.model.bpmn.instance.Task;

import com.ibm.model.annotation.Rule;
import com.ibm.model.entity.Element;
import com.ibm.model.entity.Severity;
import com.ibm.model.rules.BaseRule;

@Rule(severity = Severity.MUST, targetType = Element.TASK)
public class TaskNameNonNullRule extends BaseRule {

    public TaskNameNonNullRule() {
    }

    private static final Logger LOG = Logger.getLogger("TaskNameNonNullRule.class");

    @Override
    public boolean check(Object OUT) {
        Task targetType = (Task) OUT;
        return targetType.getName() == null || targetType.getName() == "" ?  false :  true;
    }
}