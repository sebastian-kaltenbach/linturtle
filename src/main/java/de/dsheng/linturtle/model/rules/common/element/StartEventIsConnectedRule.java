package de.dsheng.linturtle.model.rules.common.element;

import org.omg.spec.bpmn._20100524.model.TStartEvent;

import de.dsheng.linturtle.model.BaseRule;
import de.dsheng.linturtle.model.annotation.Rule;
import de.dsheng.linturtle.model.entity.Element;
import de.dsheng.linturtle.model.entity.Severity;
import de.dsheng.linturtle.utils.RuleCheckUtils;

@Rule(description = "Provide connection for start event", severity = Severity.MUST, targetType = Element.STARTEVENT)
public class StartEventIsConnectedRule extends BaseRule {

    @Override
    public boolean check(Object element) {
        TStartEvent startEvent = (TStartEvent) element;
        return RuleCheckUtils.hasConnection(startEvent.getOutgoing());
    }   
    
}
