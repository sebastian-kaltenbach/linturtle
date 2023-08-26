package de.dsheng.linturtle.domain.model.checker;

import de.dsheng.linturtle.application.utils.AttributeUtils;
import de.dsheng.linturtle.application.utils.BpmnExtractor;
import de.dsheng.linturtle.domain.model.Violation;
import de.dsheng.linturtle.domain.model.ViolationSource;
import de.dsheng.linturtle.domain.model.annotation.ElementConvention;
import de.dsheng.linturtle.domain.model.annotation.Operation;
import de.dsheng.linturtle.domain.model.annotation.Rule;
import de.dsheng.linturtle.domain.model.entity.Element;
import de.dsheng.linturtle.domain.model.omg.spec.bpmn._20100524.model.TProcess;
import de.dsheng.linturtle.domain.model.omg.spec.bpmn._20100524.model.TSequenceFlow;
import org.apache.maven.plugin.logging.Log;
import org.javatuples.Pair;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class OverlapChecker extends BaseChecker {

    private Log log;

    public OverlapChecker(Rule rule, Log log) {
        super(rule);
        this.log = log;
    }

    @Override
    public ViolationSource check(TProcess process) {
        final Collection<Violation> violationCollection = new ArrayList<>();
        AtomicInteger testCount = new AtomicInteger();

        var operation = new Operation("check", "-");
        var elementConvention = new ElementConvention("Element", "Check if Element contains redundant sequence flows.", List.of(operation));

        log.info("Check convention with name SequenceFlow.");
        final Collection<Pair<String, Pair<String, String>>> sequenceFlowCollection = new ArrayList<>();
        var targetElements = BpmnExtractor.extractBpmnElementByTargetElement(process, Element.SEQUENCEFLOW);
        testCount.updateAndGet(v -> v + targetElements.size());

        targetElements.stream().map(flowElement -> (TSequenceFlow) flowElement).forEach(sequenceFlow -> {
            log.info(String.format("Target element found with id %s and name %s.", sequenceFlow.getId(), sequenceFlow.getName()));
            Pair<String, String> refPair = new Pair<>(sequenceFlow.getSourceRef().toString(), sequenceFlow.getTargetRef().toString());
            sequenceFlowCollection.add(new Pair<String, Pair<String, String>>(sequenceFlow.getId(), refPair));
        });
        sequenceFlowCollection.forEach(sequenceFlow -> {
            sequenceFlowCollection.forEach(sequenceFlowDummy -> {
                var result = tupleValuesAreEqual(sequenceFlow, sequenceFlowDummy);
                if(result) {
                    violationCollection.add(new Violation(rule.name(), elementConvention, operation, sequenceFlow.getValue0()));
                }
            });
        });

        return new ViolationSource(testCount.get(), violationCollection);
    }

    /**
     * .
     * @param refFlow
     * @param flow
     * @return
     */
    private boolean tupleValuesAreEqual(Pair<String, Pair<String, String>> refFlow, Pair<String, Pair<String, String>> flow) {
        if(!refFlow.getValue0().equals(flow.getValue0())) {
            return refFlow.getValue1().equals(flow.getValue1());
        }
        return false;
    }
}
