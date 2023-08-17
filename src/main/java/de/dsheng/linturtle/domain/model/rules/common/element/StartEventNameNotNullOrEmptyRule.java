package de.dsheng.linturtle.domain.model.rules.common.element;

import de.dsheng.linturtle.domain.model.omg.spec.bpmn._20100524.model.TStartEvent;

import de.dsheng.linturtle.domain.model.BaseRule;
import de.dsheng.linturtle.domain.model.annotation.Rule;
import de.dsheng.linturtle.domain.model.entity.Element;
import de.dsheng.linturtle.domain.model.entity.Severity;
import de.dsheng.linturtle.utils.RuleCheckUtils;

@Rule(severity = Severity.MUST, targetType = Element.STARTEVENT, description = "Provide name for start event")
public class StartEventNameNotNullOrEmptyRule extends BaseRule {

    @Override
    public boolean check(Object element) {
        TStartEvent startEvent = (TStartEvent) element;
        return RuleCheckUtils.notNullOrEmpty(startEvent.getName());
    }
}