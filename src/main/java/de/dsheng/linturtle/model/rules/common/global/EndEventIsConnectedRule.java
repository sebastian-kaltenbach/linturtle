package de.dsheng.linturtle.model.rules.common.global;

import java.util.HashMap;
import java.util.Map;

import org.omg.spec.bpmn._20100524.model.TEndEvent;
import org.omg.spec.bpmn._20100524.model.TProcess;

import de.dsheng.linturtle.model.annotation.Rule;
import de.dsheng.linturtle.model.entity.Element;
import de.dsheng.linturtle.model.entity.Severity;
import de.dsheng.linturtle.model.rules.GlobalRule;
import de.dsheng.linturtle.utils.ProcessUtils;
import de.dsheng.linturtle.utils.RuleCheckUtils;

@Rule(description = "Checks, if Endevent is connected to the process", severity = Severity.MUST, targetType = Element.ENDEVENT)
public class EndEventIsConnectedRule extends GlobalRule {

    @Override
    public Map<String, Boolean> check(TProcess process) {
        Map<String, Boolean> resultSet = new HashMap<>();
        ProcessUtils.getProcessElementsByElement(process, Element.ENDEVENT).stream().map(flowElement -> (TEndEvent) flowElement.getValue())
            .forEach(endEvent -> {
                resultSet.put(endEvent.getId(), RuleCheckUtils.hasConnection(endEvent.getIncoming()));
            });
        return resultSet;
    }    
}
