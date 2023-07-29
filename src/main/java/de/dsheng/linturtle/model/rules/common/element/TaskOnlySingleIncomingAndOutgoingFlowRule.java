package de.dsheng.linturtle.model.rules.common.element;

import org.omg.spec.bpmn._20100524.model.TTask;

import de.dsheng.linturtle.model.ElementRule;
import de.dsheng.linturtle.model.annotation.Rule;
import de.dsheng.linturtle.model.entity.Element;
import de.dsheng.linturtle.model.entity.Severity;
import de.dsheng.linturtle.utils.RuleCheckUtils;

@Rule(severity = Severity.MUST, targetType = Element.TASK, description = "Checks, if Tasks have a non-null name")
public class TaskOnlySingleIncomingAndOutgoingFlowRule extends ElementRule {

    @Override
    public boolean check(Object element) {
        TTask task = (TTask) element;
        return !(RuleCheckUtils.hasMultipleConnections(task.getIncoming()) && 
            RuleCheckUtils.hasMultipleConnections(task.getOutgoing()));
    }
}