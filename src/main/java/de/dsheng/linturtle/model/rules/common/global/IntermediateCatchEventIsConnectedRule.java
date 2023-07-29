package de.dsheng.linturtle.model.rules.common.global;

import java.util.HashMap;
import java.util.Map;

import org.omg.spec.bpmn._20100524.model.TIntermediateCatchEvent;
import org.omg.spec.bpmn._20100524.model.TProcess;

import de.dsheng.linturtle.model.GlobalRule;
import de.dsheng.linturtle.model.annotation.Rule;
import de.dsheng.linturtle.model.entity.Element;
import de.dsheng.linturtle.model.entity.Severity;
import de.dsheng.linturtle.utils.ProcessUtils;
import de.dsheng.linturtle.utils.RuleCheckUtils;

@Rule(description = "Checks, if Gateway is connected to the process", severity = Severity.MUST, targetType = Element.INTERMEDIATECATCHEVENT)
public class IntermediateCatchEventIsConnectedRule extends GlobalRule {

    @Override
    public Map<String, Boolean> check(TProcess process) {
        Map<String, Boolean> resultSet = new HashMap<>();
        ProcessUtils.getProcessElementsByElement(process, Element.INTERMEDIATECATCHEVENT).stream().map(flowElement -> (TIntermediateCatchEvent) flowElement.getValue())
            .forEach(intermediateCatchEvent -> {
                resultSet.put(intermediateCatchEvent.getId(), RuleCheckUtils.hasConnection(intermediateCatchEvent.getIncoming()) && RuleCheckUtils.hasConnection(intermediateCatchEvent.getOutgoing()));
            });
        return resultSet;
    }    
}
