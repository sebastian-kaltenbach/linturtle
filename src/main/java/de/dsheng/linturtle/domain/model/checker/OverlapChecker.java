package de.dsheng.linturtle.domain.model.checker;

import de.dsheng.linturtle.application.utils.AttributeUtils;
import de.dsheng.linturtle.application.utils.BpmnExtractor;
import de.dsheng.linturtle.domain.model.Violation;
import de.dsheng.linturtle.domain.model.annotation.Operation;
import de.dsheng.linturtle.domain.model.annotation.Rule;
import de.dsheng.linturtle.domain.model.entity.Element;
import de.dsheng.linturtle.domain.model.omg.spec.bpmn._20100524.model.TProcess;
import de.dsheng.linturtle.domain.model.omg.spec.bpmn._20100524.model.TSequenceFlow;
import org.apache.maven.plugin.logging.Log;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class OverlapChecker extends BaseChecker {

    private Log log;

    public OverlapChecker(Rule rule, Log log) {
        super(rule);
        this.log = log;
    }

    @Override
    public Collection<Violation> check(TProcess process) {
        final Collection<Violation> violations = new ArrayList<>();
        rule.elementConventions().forEach(elementConvention -> {
            log.info("Check convention with name SequenceFlow.");
            final Collection<Pair<String, Pair<String, String>>> sequenceFlowCollection = new ArrayList<>();
            var targetElements = BpmnExtractor.extractBpmnElementByTargetElement(process, Element.SEQUENCEFLOW);

            targetElements.stream().map(flowElement -> (TSequenceFlow) flowElement).forEach(sequenceFlow -> {
                log.info(String.format("Target element found with id %s and name %s.", sequenceFlow.getId(), sequenceFlow.getName()));
                Pair<String, String> refPair = new Pair<>(sequenceFlow.getSourceRef().toString(), sequenceFlow.getTargetRef().toString());
                sequenceFlowCollection.add(new Pair<String, Pair<String, String>>(sequenceFlow.getId(), refPair));
            });

            sequenceFlowCollection.forEach(sequenceFlow -> {
                sequenceFlowCollection.forEach(sequenceFlowDummy -> {
                    var result = tupleValuesAreEqual(sequenceFlow, sequenceFlowDummy);
                    if(result) {
                        var operation = new Operation("check", "-");
                        violations.add(new Violation(rule.name(), elementConvention, operation, sequenceFlow.getValue0()));
                    }
                });
            });
        });
        return violations;
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
