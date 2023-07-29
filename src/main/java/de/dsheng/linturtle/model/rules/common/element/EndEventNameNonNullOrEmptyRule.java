package de.dsheng.linturtle.model.rules.common.element;

import org.omg.spec.bpmn._20100524.model.TEndEvent;

import de.dsheng.linturtle.model.annotation.Rule;
import de.dsheng.linturtle.model.entity.Element;
import de.dsheng.linturtle.model.entity.Severity;
import de.dsheng.linturtle.model.rules.ElementRule;
import de.dsheng.linturtle.utils.RuleCheckUtils;

@Rule(severity = Severity.MUST, targetType = Element.ENDEVENT, description = "Checks, if End Events have a non-null name")
public class EndEventNameNonNullOrEmptyRule extends ElementRule {

    @Override
    public boolean check(Object OUT) {
        TEndEvent endEvent = (TEndEvent) OUT;
        return RuleCheckUtils.nonNullOrEmpty(endEvent.getName());
    }
}