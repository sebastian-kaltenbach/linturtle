package de.dsheng.linturtle.model.rules.common.element;

import org.omg.spec.bpmn._20100524.model.TEndEvent;

import de.dsheng.linturtle.model.ElementRule;
import de.dsheng.linturtle.model.annotation.Rule;
import de.dsheng.linturtle.model.entity.Element;
import de.dsheng.linturtle.model.entity.Severity;
import de.dsheng.linturtle.utils.RuleCheckUtils;

@Rule(severity = Severity.MUST, targetType = Element.ENDEVENT, description = "Provide name for end event")
public class EndEventNameNotNullOrEmptyRule extends ElementRule {

    @Override
    public boolean check(Object element) {
        TEndEvent endEvent = (TEndEvent) element;
        return RuleCheckUtils.notNullOrEmpty(endEvent.getName());
    }
}