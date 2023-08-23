package de.dsheng.linturtle.domain.model.checker;

import de.dsheng.linturtle.application.utils.AttributeUtils;
import de.dsheng.linturtle.application.utils.BpmnExtractor;
import de.dsheng.linturtle.domain.model.Violation;
import de.dsheng.linturtle.domain.model.ViolationSource;
import de.dsheng.linturtle.domain.model.annotation.Operation;
import de.dsheng.linturtle.domain.model.annotation.Rule;
import de.dsheng.linturtle.domain.model.entity.Element;
import de.dsheng.linturtle.domain.model.omg.spec.bpmn._20100524.model.TProcess;
import org.apache.maven.plugin.logging.Log;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;

public class OutgoingGatewayHasNamedFlowsChecker extends BaseChecker {

    private Log log;

    public OutgoingGatewayHasNamedFlowsChecker(Rule rule, Log log) {
        super(rule);
        this.log = log;
    }

    @Override
    public ViolationSource check(TProcess process) {
        final Collection<Violation> violationCollection = new ArrayList<>();
        AtomicInteger testCount = new AtomicInteger();

        rule.elementConventions().forEach(elementConvention -> {
            log.info(String.format("Check convention with name %s.", elementConvention.name()));

            var targetElements = BpmnExtractor.extractOutgoingGatewaysByTargetElement(process, Element.valueOf(elementConvention.name().toUpperCase()));
            final Collection<String> sequenceFlowIdsCollection = new ArrayList<>();
            testCount.updateAndGet(v -> v + targetElements.size());

            targetElements.forEach(targetElement -> {
                log.info(String.format("Target element found with id %s and name %s.", targetElement.getId(), targetElement.getName()));
                sequenceFlowIdsCollection.addAll(targetElement.getOutgoing().stream().map(QName::getLocalPart).toList());
            });
            var sequenceFlowCollection = BpmnExtractor.extractAllSequenceFlowsByIdList(process, sequenceFlowIdsCollection);
            sequenceFlowCollection.forEach(sequenceFlow -> {
                elementConvention.operations().forEach(operation -> {
                    if(!AttributeUtils.isNullOrBlank(sequenceFlow.getName())) {
                        var result = AttributeUtils.performCheckBasedOnOperation(operation, sequenceFlow.getName());
                        log.info(String.format("Operation of type [%s] with value [%s] provided result [%s].", operation.type(), operation.value(), result));
                        if(!result){
                            violationCollection.add(new Violation(rule.name(), elementConvention, operation, sequenceFlow.getId()));
                        }
                    }
                    else {
                        log.info(String.format("Target element with id [%s] does not have a valid attribute name.", sequenceFlow.getId()));
                        violationCollection.add(new Violation(rule.name(), elementConvention, operation, sequenceFlow.getId()));
                    }
                });
            });
        });
        return new ViolationSource(testCount.get(), violationCollection);
    }
}
