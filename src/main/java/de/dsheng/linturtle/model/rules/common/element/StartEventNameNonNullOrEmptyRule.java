package de.dsheng.linturtle.model.rules.common.element;

import org.omg.spec.bpmn._20100524.model.TStartEvent;

import de.dsheng.linturtle.model.annotation.Rule;
import de.dsheng.linturtle.model.entity.Element;
import de.dsheng.linturtle.model.entity.Severity;
import de.dsheng.linturtle.model.rules.ElementRule;
import de.dsheng.linturtle.utils.RuleCheckUtils;

@Rule(severity = Severity.MUST, targetType = Element.STARTEVENT, description = "Checks, if Start Events have a non-null name")
public class StartEventNameNonNullOrEmptyRule extends ElementRule {

    @Override
    public boolean check(Object element) {
        TStartEvent startEvent = (TStartEvent) element;
        return RuleCheckUtils.nonNullOrEmpty(startEvent.getName());
    }
}