package de.dsheng.linturtle.domain.model.rules.common.element;

import de.dsheng.linturtle.domain.model.omg.spec.bpmn._20100524.model.TEndEvent;

import de.dsheng.linturtle.domain.model.BaseRule;
import de.dsheng.linturtle.domain.model.annotation.Rule;
import de.dsheng.linturtle.domain.model.entity.Element;
import de.dsheng.linturtle.domain.model.entity.Severity;
import de.dsheng.linturtle.utils.RuleCheckUtils;

@Rule(severity = Severity.MUST, targetType = Element.ENDEVENT, description = "Provide name for end event")
public class EndEventNameNotNullOrEmptyRule extends BaseRule {

    @Override
    public boolean check(Object element) {
        TEndEvent endEvent = (TEndEvent) element;
        return RuleCheckUtils.notNullOrEmpty(endEvent.getName());
    }
}