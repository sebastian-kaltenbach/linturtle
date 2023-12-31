package de.dsheng.linturtle.utils;

import java.util.List;

import org.omg.spec.bpmn._20100524.model.TFlowElement;
import org.omg.spec.bpmn._20100524.model.TGateway;
import org.omg.spec.bpmn._20100524.model.TProcess;
import org.omg.spec.bpmn._20100524.model.TSequenceFlow;

import de.dsheng.linturtle.model.entity.Element;
import jakarta.xml.bind.JAXBElement;

public final class ProcessUtils {

    public static List<JAXBElement<? extends TFlowElement>> getProcessElementsByElement(TProcess process, Element element) {
        return process.getFlowElement().stream().filter(flowElement -> 
            flowElement.getName().getLocalPart().toLowerCase().contains(element.toString().toLowerCase())).toList();
    }

    public static List<TGateway> getAllOutgoingGateways(TProcess process, Element element) {
            return getProcessElementsByElement(process, element).stream()
                .map(flowElement -> (TGateway) flowElement.getValue())
                .filter(flowElement -> isGatewayOutgoing(flowElement))
                .toList();
    }

    public static List<TSequenceFlow> getAllSequenceFlowsByIdList(TProcess process, List<String> flowIds) {
        return getProcessElementsByElement(process, Element.SEQUENCEFLOW).stream()
            .map(flowElement -> (TSequenceFlow) flowElement.getValue()).filter(sequenceFlow -> flowIds.contains(sequenceFlow.getId())).toList();
    }

    private static boolean isGatewayOutgoing(TGateway gateway) {
        return gateway.getOutgoing().size() > gateway.getIncoming().size();
    }
}
