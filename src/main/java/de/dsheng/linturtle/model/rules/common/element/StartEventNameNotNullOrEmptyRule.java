package de.dsheng.linturtle.model.rules.common.element;

import org.omg.spec.bpmn._20100524.model.TStartEvent;

import de.dsheng.linturtle.model.ElementRule;
import de.dsheng.linturtle.model.annotation.Rule;
import de.dsheng.linturtle.model.entity.Element;
import de.dsheng.linturtle.model.entity.Severity;
import de.dsheng.linturtle.utils.RuleCheckUtils;

@Rule(severity = Severity.MUST, targetType = Element.STARTEVENT, description = "Provide name for start event")
public class StartEventNameNotNullOrEmptyRule extends ElementRule {

    @Override
    public boolean check(Object element) {
        TStartEvent startEvent = (TStartEvent) element;
        return RuleCheckUtils.notNullOrEmpty(startEvent.getName());
    }
}