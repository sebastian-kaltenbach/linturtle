package com.ibm.rules;

import com.ibm.model.Rule;
import com.ibm.model.entity.Element;
import com.ibm.model.entity.Severity;

@Rule(severity = Severity.MUST, target = "name", type = Element.TASK)
public class TaskMustHaveNameRule extends BasicRule {

}
