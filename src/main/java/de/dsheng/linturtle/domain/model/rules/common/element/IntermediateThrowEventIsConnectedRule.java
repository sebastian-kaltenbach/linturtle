package de.dsheng.linturtle.domain.model.rules.common.element;

import de.dsheng.linturtle.domain.model.omg.spec.bpmn._20100524.model.TIntermediateThrowEvent;

import de.dsheng.linturtle.domain.model.BaseRule;
import de.dsheng.linturtle.domain.model.annotation.Rule;
import de.dsheng.linturtle.domain.model.entity.Element;
import de.dsheng.linturtle.domain.model.entity.Severity;
import de.dsheng.linturtle.utils.RuleCheckUtils;

@Rule(description = "Provide connection for intermediate throw event", severity = Severity.MUST, targetType = Element.INTERMEDIATETHROWEVENT)
public class IntermediateThrowEventIsConnectedRule extends BaseRule {

    @Override
    public boolean check(Object element) {
        TIntermediateThrowEvent intermediateThrowEvent = (TIntermediateThrowEvent) element;
        return RuleCheckUtils.hasConnection(intermediateThrowEvent.getIncoming()) && RuleCheckUtils.hasConnection(intermediateThrowEvent.getOutgoing());
    }    
} 
