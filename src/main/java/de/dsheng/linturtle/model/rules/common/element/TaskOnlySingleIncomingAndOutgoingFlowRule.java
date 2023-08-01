package de.dsheng.linturtle.model.rules.common.element;

import org.omg.spec.bpmn._20100524.model.TTask;

import de.dsheng.linturtle.model.BaseRule;
import de.dsheng.linturtle.model.annotation.Rule;
import de.dsheng.linturtle.model.entity.Element;
import de.dsheng.linturtle.model.entity.Severity;
import de.dsheng.linturtle.utils.RuleCheckUtils;

@Rule(severity = Severity.MUST, targetType = Element.TASK, description = "Provide only one input and output flow for task")
public class TaskOnlySingleIncomingAndOutgoingFlowRule extends BaseRule {

    @Override
    public boolean check(Object element) {
        TTask task = (TTask) element;
        return !(RuleCheckUtils.hasMultipleConnections(task.getIncoming()) && 
            RuleCheckUtils.hasMultipleConnections(task.getOutgoing()));
    }
}