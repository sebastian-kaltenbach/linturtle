package de.dsheng.linturtle.model.rules.common;

import java.util.ArrayList;
import java.util.List;

import org.omg.spec.bpmn._20100524.model.TProcess;

import de.dsheng.linturtle.model.RuleResult;
import de.dsheng.linturtle.model.annotation.Rule;
import de.dsheng.linturtle.model.entity.Element;
import de.dsheng.linturtle.model.entity.Severity;
import de.dsheng.linturtle.model.rules.BaseRule;
import de.dsheng.linturtle.utils.ProcessUtils;
import de.dsheng.linturtle.utils.RuleCheckUtils;

@Rule(description = "Check if exclusive gateway flows have a name", severity = Severity.MUST, targetType = Element.PROCESS)
public class ExclusiveGatewayFlowsNameNonNullOrEmpty extends BaseRule{

    @Override
    public RuleResult check(Object OUT) {
        TProcess process = (TProcess) OUT;
        List<String> flows = new ArrayList<>();
        
        ProcessUtils.getAllOutgoingGateways(process, Element.EXCLUSIVEGATEWAY).stream()
            .forEach(gateway -> {
                gateway.getOutgoing().stream().forEach(flow -> {
                    flows.add(flow.getLocalPart());
                });
            }
        );
        ProcessUtils.getAllSequenceFlowsByIdList(process, flows).stream().forEach(sequenceFlow -> {
            RuleCheckUtils.nonNullOrEmpty(sequenceFlow.getName());
        });
        return new RuleResult(true, flows.get(0));
    }
}
