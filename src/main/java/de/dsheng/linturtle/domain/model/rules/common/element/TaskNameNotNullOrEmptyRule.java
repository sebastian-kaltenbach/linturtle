package de.dsheng.linturtle.domain.model.rules.common.element;

import de.dsheng.linturtle.domain.model.BaseRule;
import de.dsheng.linturtle.domain.model.annotation.Rule;
import de.dsheng.linturtle.domain.model.entity.Element;
import de.dsheng.linturtle.domain.model.entity.Severity;
import de.dsheng.linturtle.domain.model.omg.spec.bpmn._20100524.model.TTask;

import de.dsheng.linturtle.utils.RuleCheckUtils;

@Rule(severity = Severity.MUST, targetType = Element.TASK, description = "Provide name for task")
public class TaskNameNotNullOrEmptyRule extends BaseRule {

    @Override
    public boolean check(Object element) {
        TTask task = (TTask) element;
        return RuleCheckUtils.notNullOrEmpty(task.getName());
    }
}