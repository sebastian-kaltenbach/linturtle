package de.dsheng.linturtle.application.utils;

import java.util.Collection;
import java.util.List;

import de.dsheng.linturtle.domain.model.omg.spec.bpmn._20100524.model.*;

import de.dsheng.linturtle.domain.model.entity.Element;
import jakarta.xml.bind.JAXBElement;

public final class BpmnExtractor {

    /**
     * .
     * @param process
     * @param element
     * @return
     */
    public static Collection<? extends TFlowElement> extractBpmnElementByTargetElement(TProcess process, Element element) {
        return process.getFlowElement().stream()
            .map(JAXBElement::getValue)
            .filter(flowElement -> element.Clazz().isInstance(flowElement))
            .toList();
    }

    /**
     * .
     * @param process
     * @param element
     * @return
     */
    public static Collection<TGateway> extractOutgoingGatewaysByTargetElement(TProcess process, Element element) {
            return getProcessElementsByElement(process, element).stream()
                .map(flowElement -> (TGateway) flowElement.getValue())
                .filter(BpmnExtractor::isGatewayOutgoing)
                .toList();
    }

    /**
     * .
     * @param process
     * @param element
     * @return
     */
    public static Collection<TEventBasedGateway> extractEventBasedGatewaysByTargetElement(TProcess process, Element element) {
        return getProcessElementsByElement(process, element).stream()
                .map(flowElement -> (TEventBasedGateway) flowElement.getValue())
                .toList();
    }

    /**
     * .
     * @param process
     * @param flowIds
     * @return
     */
    public static Collection<TSequenceFlow> extractAllSequenceFlowsByIdList(TProcess process, Collection<String> flowIds) {
        return getProcessElementsByElement(process, Element.SEQUENCEFLOW).stream()
            .map(flowElement -> (TSequenceFlow) flowElement.getValue()).filter(sequenceFlow -> flowIds.contains(sequenceFlow.getId())).toList();
    }

    /**
     * .
     * @param gateway
     * @return
     */
    private static boolean isGatewayOutgoing(TGateway gateway) {
        return gateway.getOutgoing().size() > gateway.getIncoming().size();
    }

    /**
     * .
     * @param process
     * @param element
     * @return
     */
    private static List<JAXBElement<? extends TFlowElement>> getProcessElementsByElement(TProcess process, Element element) {
        return process.getFlowElement().stream().filter(flowElement ->
                flowElement.getName().getLocalPart().toLowerCase().contains(element.toString().toLowerCase())).toList();
    }
}
