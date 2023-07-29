package de.dsheng.linturtle.model.rules.common.element;

import org.omg.spec.bpmn._20100524.model.TEndEvent;

import de.dsheng.linturtle.model.ElementRule;
import de.dsheng.linturtle.model.annotation.Rule;
import de.dsheng.linturtle.model.entity.Element;
import de.dsheng.linturtle.model.entity.Severity;
import de.dsheng.linturtle.utils.RuleCheckUtils;

@Rule(description = "Checks, if Endevent is connected to the process", severity = Severity.MUST, targetType = Element.ENDEVENT)
public class EndEventIsConnectedRule extends ElementRule {

    @Override
    public boolean check(Object element) {
        TEndEvent endEvent = (TEndEvent) element;
        return RuleCheckUtils.hasConnection(endEvent.getIncoming());
    }    
}
