package de.dsheng.linturtle.model.rules.common.complex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.omg.spec.bpmn._20100524.model.TProcess;

import de.dsheng.linturtle.model.ComplexRule;
import de.dsheng.linturtle.model.annotation.Rule;
import de.dsheng.linturtle.model.entity.Element;
import de.dsheng.linturtle.model.entity.Severity;
import de.dsheng.linturtle.utils.ProcessUtils;
import de.dsheng.linturtle.utils.RuleCheckUtils;

@Rule(description = "Provide name for exclusive gateway outgoing flows", severity = Severity.MUST, targetType = Element.PROCESS)
public class ExclusiveGatewayOutgoingFlowsNameNotNullOrEmptyRule extends ComplexRule {

    @Override
    public Map<String, Boolean> check(TProcess process) {
        Map<String, Boolean> resultSet = new HashMap<>();
        List<String> flows = new ArrayList<>();

        ProcessUtils.getAllOutgoingGateways(process, Element.EXCLUSIVEGATEWAY).stream()
            .forEach(exclusiveGateway -> {
                exclusiveGateway.getOutgoing().stream().forEach(flow -> {
                    flows.add(flow.getLocalPart());
                });
            });
        ProcessUtils.getAllSequenceFlowsByIdList(process, flows).stream().forEach(sequenceFlow -> {
            resultSet.put(sequenceFlow.getId(), RuleCheckUtils.notNullOrEmpty(sequenceFlow.getName()));
        });
        return resultSet;
    } 
}
