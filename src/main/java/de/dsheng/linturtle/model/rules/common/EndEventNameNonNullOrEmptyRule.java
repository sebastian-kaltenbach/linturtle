package de.dsheng.linturtle.model.rules.common;

import org.omg.spec.bpmn._20100524.model.TEndEvent;

import de.dsheng.linturtle.model.RuleResult;
import de.dsheng.linturtle.model.annotation.Rule;
import de.dsheng.linturtle.model.entity.Element;
import de.dsheng.linturtle.model.entity.Severity;
import de.dsheng.linturtle.model.rules.BaseRule;
import de.dsheng.linturtle.utils.RuleCheckUtils;

@Rule(severity = Severity.MUST, targetType = Element.ENDEVENT, description = "Checks, if End Events have a non-null name")
public class EndEventNameNonNullOrEmptyRule extends BaseRule {

    public EndEventNameNonNullOrEmptyRule() {
    }

    @Override
    public RuleResult check(Object OUT) {
        TEndEvent endEvent = (TEndEvent) OUT;
        return new RuleResult(!RuleCheckUtils.nonNullOrEmpty(endEvent.getName()), endEvent.getId());
    }
}