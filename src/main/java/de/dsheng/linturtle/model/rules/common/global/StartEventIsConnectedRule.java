package de.dsheng.linturtle.model.rules.common.global;

import java.util.HashMap;
import java.util.Map;

import org.omg.spec.bpmn._20100524.model.TProcess;
import org.omg.spec.bpmn._20100524.model.TStartEvent;

import de.dsheng.linturtle.model.GlobalRule;
import de.dsheng.linturtle.model.annotation.Rule;
import de.dsheng.linturtle.model.entity.Element;
import de.dsheng.linturtle.model.entity.Severity;
import de.dsheng.linturtle.utils.ProcessUtils;
import de.dsheng.linturtle.utils.RuleCheckUtils;

@Rule(description = "Checks, if Startevent is connected to the process", severity = Severity.MUST, targetType = Element.STARTEVENT)
public class StartEventIsConnectedRule extends GlobalRule {

    @Override
    public Map<String, Boolean> check(TProcess process) {
        Map<String, Boolean> resultSet = new HashMap<>();
        ProcessUtils.getProcessElementsByElement(process, Element.STARTEVENT).stream().map(flowElement -> (TStartEvent) flowElement.getValue())
            .forEach(startevent -> {
                resultSet.put(startevent.getId(), RuleCheckUtils.hasConnection(startevent.getOutgoing()));
            });
        return resultSet;
    }   
    
}
