package de.dsheng.model.rules.common;

import org.camunda.bpm.model.bpmn.instance.Task;

import de.dsheng.model.RuleResult;
import de.dsheng.model.annotation.Rule;
import de.dsheng.model.entity.Element;
import de.dsheng.model.entity.Severity;
import de.dsheng.model.rules.BaseRule;

@Rule(severity = Severity.MUST, targetType = Element.TASK, description = "Checks, if Tasks have a non-null name")
public class TaskNameNonNullRule extends BaseRule {

    public TaskNameNonNullRule() {
    }
    
    @Override
    public RuleResult check(Object OUT) {
        Task targetType = (Task) OUT;
        return new RuleResult(targetType.getName() == null || targetType.getName() == "" ?  false :  true, targetType.getId());
    }
}