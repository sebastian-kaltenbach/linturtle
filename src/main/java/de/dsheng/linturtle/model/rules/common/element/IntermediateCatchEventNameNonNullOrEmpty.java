package de.dsheng.linturtle.model.rules.common.element;

import org.omg.spec.bpmn._20100524.model.TIntermediateCatchEvent;

import de.dsheng.linturtle.model.ElementRule;
import de.dsheng.linturtle.model.annotation.Rule;
import de.dsheng.linturtle.model.entity.Element;
import de.dsheng.linturtle.model.entity.Severity;
import de.dsheng.linturtle.utils.RuleCheckUtils;

@Rule(description = "Checks if intermediate catch event name is not null or empty", severity = Severity.MUST, targetType = Element.INTERMEDIATECATCHEVENT)
public class IntermediateCatchEventNameNonNullOrEmpty extends ElementRule {

    @Override
    public boolean check(Object element) {
        TIntermediateCatchEvent intermediateCatchEvent = (TIntermediateCatchEvent) element;
        return RuleCheckUtils.nonNullOrEmpty(intermediateCatchEvent.getName());
    }
    
}
