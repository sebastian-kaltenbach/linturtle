package de.dsheng.linturtle.model.rules.common;

import org.omg.spec.bpmn._20100524.model.TIntermediateCatchEvent;
import de.dsheng.linturtle.model.RuleResult;
import de.dsheng.linturtle.model.annotation.Rule;
import de.dsheng.linturtle.model.entity.Element;
import de.dsheng.linturtle.model.entity.Severity;
import de.dsheng.linturtle.model.rules.BaseRule;
import de.dsheng.linturtle.utils.RuleCheckUtils;

@Rule(description = "Checks if intermediate catch event name is not null or empty", severity = Severity.MUST, targetType = Element.INTERMEDIATECATCHEVENT)
public class IntermediateCatchEventNameNonNullOrEmpty extends BaseRule {

    @Override
    public RuleResult check(Object OUT) {
        TIntermediateCatchEvent intermediateCatchEvent = (TIntermediateCatchEvent) OUT;
        return new RuleResult(!RuleCheckUtils.nonNullOrEmpty(intermediateCatchEvent.getName()) ?  false :  true, intermediateCatchEvent.getId());
    }
    
}
