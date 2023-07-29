package de.dsheng.linturtle.model.rules.common.element;

import org.omg.spec.bpmn._20100524.model.TIntermediateThrowEvent;
import de.dsheng.linturtle.model.annotation.Rule;
import de.dsheng.linturtle.model.entity.Element;
import de.dsheng.linturtle.model.entity.Severity;
import de.dsheng.linturtle.model.rules.ElementRule;
import de.dsheng.linturtle.utils.RuleCheckUtils;

@Rule(description = "Checks if intermediate throw event name is not null or empty", severity = Severity.MUST, targetType = Element.INTERMEDIATETHROWEVENT)
public class IntermediateThrowEventNameNonNullOrEmpty extends ElementRule {

    @Override
    public boolean check(Object element) {
        TIntermediateThrowEvent intermediateThrowEvent = (TIntermediateThrowEvent) element;
        return RuleCheckUtils.nonNullOrEmpty(intermediateThrowEvent.getName());
    }
    
}
