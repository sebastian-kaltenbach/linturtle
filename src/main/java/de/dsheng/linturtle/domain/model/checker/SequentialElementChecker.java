package de.dsheng.linturtle.domain.model.checker;

import de.dsheng.linturtle.application.utils.BpmnExtractor;
import de.dsheng.linturtle.domain.model.Violation;
import de.dsheng.linturtle.domain.model.ViolationSource;
import de.dsheng.linturtle.domain.model.annotation.ElementConvention;
import de.dsheng.linturtle.domain.model.annotation.Operation;
import de.dsheng.linturtle.domain.model.annotation.Rule;
import de.dsheng.linturtle.domain.model.entity.Element;
import de.dsheng.linturtle.domain.model.omg.spec.bpmn._20100524.model.TProcess;
import de.dsheng.linturtle.domain.model.omg.spec.bpmn._20100524.model.TTask;
import org.apache.maven.plugin.logging.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Checker for number of incoming and outgoing flows of elements
 */
public class SequentialElementChecker extends BaseChecker {

    private Log log;

    public SequentialElementChecker(Rule rule, Log log) {
        super(rule);
        this.log = log;
    }

    @Override
    public ViolationSource check(TProcess process) {
        final Collection<Violation> violationCollection = new ArrayList<>();
        AtomicInteger testCount = new AtomicInteger();

        log.info(String.format("Check convention with name %s.", Element.TASK));
        var targetElements = BpmnExtractor.extractBpmnElementByTargetElement(process, Element.TASK);
        testCount.updateAndGet(v -> v + targetElements.size());
        var operation = new Operation("check", "-");
        var elementConvention = new ElementConvention("Task", "Check if Task only has single incoming and outgoing flows.", List.of(operation));

        targetElements.stream().map(targetElement -> (TTask) targetElement).forEach(task -> {
            log.info(String.format("Target element found with id %s and name %s.", task.getId(), task.getName()));

            var result = task.getIncoming().size() > 1 || task.getOutgoing().size() > 1;
            log.info(String.format("Operation of type [%s] with value [%s] provided result [%s].", operation.type(), operation.value(), result));
            if(result) {
                violationCollection.add(new Violation(rule.name(), elementConvention, operation, task.getId()));
            }
        });
        return new ViolationSource(testCount.get(), violationCollection);
    }
}
