package de.dsheng.linturtle.model.rules.common;

import org.omg.spec.bpmn._20100524.model.TStartEvent;

import de.dsheng.linturtle.model.RuleResult;
import de.dsheng.linturtle.model.annotation.Rule;
import de.dsheng.linturtle.model.entity.Element;
import de.dsheng.linturtle.model.entity.Severity;
import de.dsheng.linturtle.model.rules.BaseRule;
import de.dsheng.linturtle.utils.RuleCheckUtils;

@Rule(severity = Severity.MUST, targetType = Element.STARTEVENT, description = "Checks, if Start Events have a non-null name")
public class StartEventNameNonNullOrEmptyRule extends BaseRule {

    public StartEventNameNonNullOrEmptyRule() {
    }

    @Override
    public RuleResult check(Object OUT) {
        TStartEvent startEvent = (TStartEvent) OUT;
        return new RuleResult(!RuleCheckUtils.nonNullOrEmpty(startEvent.getName()) ?  false :  true, startEvent.getId());
    }
}