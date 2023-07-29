package de.dsheng.linturtle.model.rules.common.global;

import java.util.HashMap;
import java.util.Map;

import org.omg.spec.bpmn._20100524.model.TIntermediateThrowEvent;
import org.omg.spec.bpmn._20100524.model.TProcess;

import de.dsheng.linturtle.model.GlobalRule;
import de.dsheng.linturtle.model.annotation.Rule;
import de.dsheng.linturtle.model.entity.Element;
import de.dsheng.linturtle.model.entity.Severity;
import de.dsheng.linturtle.utils.ProcessUtils;
import de.dsheng.linturtle.utils.RuleCheckUtils;

@Rule(description = "Checks, if Gateway is connected to the process", severity = Severity.MUST, targetType = Element.INTERMEDIATETHROWEVENT)
public class IntermediateThrowEventIsConnectedRule extends GlobalRule {

    @Override
    public Map<String, Boolean> check(TProcess process) {
        Map<String, Boolean> resultSet = new HashMap<>();
        ProcessUtils.getProcessElementsByElement(process, Element.INTERMEDIATETHROWEVENT).stream().map(flowElement -> (TIntermediateThrowEvent) flowElement.getValue())
            .forEach(intermediateThrowEvent -> {
                resultSet.put(intermediateThrowEvent.getId(), RuleCheckUtils.hasConnection(intermediateThrowEvent.getIncoming()) && RuleCheckUtils.hasConnection(intermediateThrowEvent.getOutgoing()));
            });
        return resultSet;
    }    
} 
