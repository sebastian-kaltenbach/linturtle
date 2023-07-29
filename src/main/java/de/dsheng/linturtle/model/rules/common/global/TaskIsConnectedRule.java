package de.dsheng.linturtle.model.rules.common.global;

import java.util.HashMap;
import java.util.Map;

import org.omg.spec.bpmn._20100524.model.TProcess;
import org.omg.spec.bpmn._20100524.model.TTask;

import de.dsheng.linturtle.model.annotation.Rule;
import de.dsheng.linturtle.model.entity.Element;
import de.dsheng.linturtle.model.entity.Severity;
import de.dsheng.linturtle.model.rules.GlobalRule;
import de.dsheng.linturtle.utils.ProcessUtils;
import de.dsheng.linturtle.utils.RuleCheckUtils;

@Rule(description = "Checks, if Task is connected to the process", severity = Severity.MUST, targetType = Element.TASK)
public class TaskIsConnectedRule extends GlobalRule {

    @Override
    public Map<String, Boolean> check(TProcess process) {
        Map<String, Boolean> resultSet = new HashMap<>();
        ProcessUtils.getProcessElementsByElement(process, Element.TASK).stream().map(flowElement -> (TTask) flowElement.getValue())
            .forEach(task -> {
                resultSet.put(task.getId(), RuleCheckUtils.hasConnection(task.getIncoming()) && RuleCheckUtils.hasConnection(task.getOutgoing()));
            });
        return resultSet;
    }   
}
