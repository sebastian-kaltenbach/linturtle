package de.dsheng.linturtle.model.rules.common;

import org.omg.spec.bpmn._20100524.model.TTask;

import de.dsheng.linturtle.model.RuleResult;
import de.dsheng.linturtle.model.annotation.Rule;
import de.dsheng.linturtle.model.entity.Element;
import de.dsheng.linturtle.model.entity.Severity;
import de.dsheng.linturtle.model.rules.BaseRule;
import de.dsheng.linturtle.utils.RuleCheckUtils;

@Rule(description = "Checks, if Task is connected to the process", severity = Severity.MUST, targetType = Element.TASK)
public class TaskIsConnectedRule extends BaseRule{

    @Override
    public RuleResult check(Object OUT) {
        TTask task = (TTask) OUT;
        return new RuleResult(RuleCheckUtils.hasConnection(task.getIncoming()) && RuleCheckUtils.hasConnection(task.getOutgoing()), 
            task.getId());
    }    
}
