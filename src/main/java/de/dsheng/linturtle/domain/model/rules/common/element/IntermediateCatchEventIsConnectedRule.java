package de.dsheng.linturtle.domain.model.rules.common.element;

import de.dsheng.linturtle.domain.model.BaseRule;
import de.dsheng.linturtle.domain.model.annotation.Rule;
import de.dsheng.linturtle.domain.model.entity.Element;
import de.dsheng.linturtle.domain.model.entity.Severity;
import de.dsheng.linturtle.domain.model.omg.spec.bpmn._20100524.model.TIntermediateCatchEvent;

import de.dsheng.linturtle.utils.RuleCheckUtils;

@Rule(description = "Provide connection for intermediate catch event", severity = Severity.MUST, targetType = Element.INTERMEDIATECATCHEVENT)
public class IntermediateCatchEventIsConnectedRule extends BaseRule {

    @Override
    public boolean check(Object element) {
        TIntermediateCatchEvent intermediateCatchEvent = (TIntermediateCatchEvent) element;
        return RuleCheckUtils.hasConnection(intermediateCatchEvent.getIncoming()) && RuleCheckUtils.hasConnection(intermediateCatchEvent.getOutgoing());
    }    
}
