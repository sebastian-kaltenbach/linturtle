package de.dsheng.linturtle.domain.model.checker;

import de.dsheng.linturtle.application.utils.BpmnExtractor;
import de.dsheng.linturtle.domain.model.Violation;
import de.dsheng.linturtle.domain.model.ViolationSource;
import de.dsheng.linturtle.domain.model.annotation.ElementConvention;
import de.dsheng.linturtle.domain.model.annotation.Operation;
import de.dsheng.linturtle.domain.model.annotation.Rule;
import de.dsheng.linturtle.domain.model.entity.Element;
import de.dsheng.linturtle.domain.model.omg.spec.bpmn._20100524.model.TEvent;
import de.dsheng.linturtle.domain.model.omg.spec.bpmn._20100524.model.TProcess;
import org.apache.maven.plugin.logging.Log;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public class EventBasedGatewaysLeadIntoEventsChecker extends BaseChecker {

    private Log log;

    public EventBasedGatewaysLeadIntoEventsChecker(Rule rule, Log log) {
        super(rule);
        this.log = log;
    }

    @Override
    public ViolationSource check(TProcess process) {
        final Collection<Violation> violationCollection = new ArrayList<>();
        final Collection<TEvent> eventCollection = BpmnExtractor.extractBpmnElementByTargetElement(process, Element.EVENT).stream().map(flowElement -> (TEvent) flowElement).toList();
        AtomicInteger testCount = new AtomicInteger();

        var operation = new Operation("check", "-");
        var elementConvention = new ElementConvention("EventBasedGateway", "Check if EventBasedGateway output flows lead into events.", List.of(operation));

        log.info(String.format("Check convention with name %s.", Element.EVENTBASEDGATEWAY));
        var targetElements = BpmnExtractor.extractEventBasedGatewaysByTargetElement(process, Element.EVENTBASEDGATEWAY);
        testCount.updateAndGet(v -> v + targetElements.size());

        final Collection<String> sequenceFlowIdsCollection = new ArrayList<>();
        targetElements.forEach(targetElement -> {
            log.info(String.format("Target element found with id %s and name %s.", targetElement.getId(), targetElement.getName()));
            sequenceFlowIdsCollection.addAll(targetElement.getOutgoing().stream().map(QName::getLocalPart).toList());
        });

        sequenceFlowIdsCollection.forEach(sequenceFlowId -> {
            var result = eventHasIncomingSequenceFlow(sequenceFlowId, eventCollection);
            if(!result) {
                violationCollection.add(new Violation(rule.name(), elementConvention, operation, sequenceFlowId));
            }
        });

        return new ViolationSource(testCount.get() , violationCollection);
    }

    /**
     * .
     * @param eventCollection
     * @param flowId
     * @return
     */
    private boolean eventHasIncomingSequenceFlow(String flowId, Collection<TEvent> eventCollection) {
        for(var event : eventCollection) {
            if(event.getIncoming().contains(flowId)) {
                return true;
            }
        }
        return false;
    }
}
