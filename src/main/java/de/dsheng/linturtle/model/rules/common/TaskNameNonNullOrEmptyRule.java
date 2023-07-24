package de.dsheng.linturtle.model.rules.common;

import org.omg.spec.bpmn._20100524.model.TTask;

import de.dsheng.linturtle.model.RuleResult;
import de.dsheng.linturtle.model.annotation.Rule;
import de.dsheng.linturtle.model.entity.Element;
import de.dsheng.linturtle.model.entity.Severity;
import de.dsheng.linturtle.model.rules.BaseRule;
import de.dsheng.linturtle.utils.RuleCheckUtils;

@Rule(severity = Severity.MUST, targetType = Element.TASK, description = "Checks, if Tasks have a non-null name")
public class TaskNameNonNullOrEmptyRule extends BaseRule {

    public TaskNameNonNullOrEmptyRule() {
    }
    @Override
    public RuleResult check(Object OUT) {
        TTask task = (TTask) OUT;
        return new RuleResult(RuleCheckUtils.nonNullOrEmpty(task.getName()) ?  false :  true, task.getId());
    }
}