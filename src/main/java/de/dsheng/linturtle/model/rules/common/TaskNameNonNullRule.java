package de.dsheng.linturtle.model.rules.common;

import org.omg.spec.bpmn._20100524.model.TTask;

import de.dsheng.linturtle.model.RuleResult;
import de.dsheng.linturtle.model.annotation.Rule;
import de.dsheng.linturtle.model.entity.Element;
import de.dsheng.linturtle.model.entity.Severity;
import de.dsheng.linturtle.model.rules.BaseRule;

@Rule(severity = Severity.MUST, targetType = Element.TASK, description = "Checks, if Tasks have a non-null name", id = 0)
public class TaskNameNonNullRule extends BaseRule {

    public TaskNameNonNullRule() {
    }
    @Override
    public RuleResult check(Object OUT) {
        TTask targetType = (TTask) OUT;
        return new RuleResult(targetType.getName() == null || targetType.getName() == "" ?  false :  true, targetType.getId());
    }
}