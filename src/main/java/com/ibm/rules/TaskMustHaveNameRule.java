package com.ibm.rules;

import com.ibm.rule.bpmn.Element;
import com.ibm.rule.bpmn.Rule;
import com.ibm.rule.bpmn.Severity;

@Rule(severity = Severity.MUST, target = "name", type = Element.TASK)
public class TaskMustHaveNameRule extends BasicRule {
        
}
