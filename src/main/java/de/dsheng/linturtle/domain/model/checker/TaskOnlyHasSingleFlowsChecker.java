package de.dsheng.linturtle.domain.model.checker;

import de.dsheng.linturtle.application.utils.AttributeUtils;
import de.dsheng.linturtle.application.utils.BpmnExtractor;
import de.dsheng.linturtle.domain.model.Violation;
import de.dsheng.linturtle.domain.model.annotation.Operation;
import de.dsheng.linturtle.domain.model.annotation.Rule;
import de.dsheng.linturtle.domain.model.entity.Element;
import de.dsheng.linturtle.domain.model.omg.spec.bpmn._20100524.model.TProcess;
import de.dsheng.linturtle.domain.model.omg.spec.bpmn._20100524.model.TTask;
import org.apache.maven.plugin.logging.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Pattern;

public class TaskOnlyHasSingleFlowsChecker extends BaseChecker {

    private Log log;

    public TaskOnlyHasSingleFlowsChecker(Rule rule, Log log) {
        super(rule);
        this.log = log;
    }

    @Override
    public Collection<Violation> check(TProcess process) {
        final Collection<Violation> violations = new ArrayList<>();
        rule.elementConventions().forEach(elementConvention -> {
            log.info(String.format("Check convention with name %s.", elementConvention.name()));
            var targetElements = BpmnExtractor.extractBpmnElementByTargetElement(process, Element.valueOf(elementConvention.name().toUpperCase()));
            targetElements.stream().map(targetElement -> (TTask) targetElement).forEach(task -> {
                log.info(String.format("Target element found with id %s and name %s.", task.getId(), task.getName()));
                var operation = new Operation("check", "-");
                var result = task.getIncoming().size() > 1 || task.getOutgoing().size() > 1;
                log.info(String.format("Operation of type [%s] with value [%s] provided result [%s].", operation.type(), operation.value(), result));
                if(result) {
                    violations.add(new Violation(rule.name(), elementConvention, operation, task.getId()));
                }
            });
        });
        return violations;
    }
}
