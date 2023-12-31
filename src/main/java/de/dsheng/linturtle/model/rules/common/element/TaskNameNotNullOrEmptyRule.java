package de.dsheng.linturtle.model.rules.common.element;

import org.omg.spec.bpmn._20100524.model.TTask;

import de.dsheng.linturtle.model.ElementRule;
import de.dsheng.linturtle.model.annotation.Rule;
import de.dsheng.linturtle.model.entity.Element;
import de.dsheng.linturtle.model.entity.Severity;
import de.dsheng.linturtle.utils.RuleCheckUtils;

@Rule(severity = Severity.MUST, targetType = Element.TASK, description = "Provide name for task")
public class TaskNameNotNullOrEmptyRule extends ElementRule {

    @Override
    public boolean check(Object element) {
        TTask task = (TTask) element;
        return RuleCheckUtils.notNullOrEmpty(task.getName());
    }
}