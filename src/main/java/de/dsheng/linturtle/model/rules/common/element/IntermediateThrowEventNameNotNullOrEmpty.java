package de.dsheng.linturtle.model.rules.common.element;

import org.omg.spec.bpmn._20100524.model.TIntermediateThrowEvent;

import de.dsheng.linturtle.model.BaseRule;
import de.dsheng.linturtle.model.annotation.Rule;
import de.dsheng.linturtle.model.entity.Element;
import de.dsheng.linturtle.model.entity.Severity;
import de.dsheng.linturtle.utils.RuleCheckUtils;

@Rule(description = "Provide name for intermediate throw event", severity = Severity.MUST, targetType = Element.INTERMEDIATETHROWEVENT)
public class IntermediateThrowEventNameNotNullOrEmpty extends BaseRule {

    @Override
    public boolean check(Object element) {
        TIntermediateThrowEvent intermediateThrowEvent = (TIntermediateThrowEvent) element;
        return RuleCheckUtils.notNullOrEmpty(intermediateThrowEvent.getName());
    }
    
}
