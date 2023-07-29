package de.dsheng.linturtle.model.rules.common.element;

import org.omg.spec.bpmn._20100524.model.TTask;

import de.dsheng.linturtle.model.ElementRule;
import de.dsheng.linturtle.model.annotation.Rule;
import de.dsheng.linturtle.model.entity.Element;
import de.dsheng.linturtle.model.entity.Severity;
import de.dsheng.linturtle.utils.RuleCheckUtils;

@Rule(severity = Severity.MUST, targetType = Element.TASK, description = "Checks, if Tasks have a non-null name")
public class TaskNameNonNullOrEmptyRule extends ElementRule {

    @Override
    public boolean check(Object OUT) {
        TTask task = (TTask) OUT;
        return RuleCheckUtils.nonNullOrEmpty(task.getName());
    }
}