package de.dsheng.linturtle.domain.model.checker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Pattern;

import de.dsheng.linturtle.application.utils.AttributeUtils;
import de.dsheng.linturtle.domain.model.annotation.Operation;
import de.dsheng.linturtle.domain.model.annotation.Rule;
import org.apache.maven.plugin.logging.Log;
import de.dsheng.linturtle.domain.model.omg.spec.bpmn._20100524.model.TProcess;
import de.dsheng.linturtle.application.utils.BpmnExtractor;
import de.dsheng.linturtle.domain.model.Violation;

import de.dsheng.linturtle.domain.model.entity.Element;

public class NamingConventionChecker extends BaseChecker {

    private Log log;

    public NamingConventionChecker(Rule rule, Log log) {
        super(rule);
        this.log = log;
    }

    @Override
    public Collection<Violation> check(TProcess process) {
        final Collection<Violation> violations = new ArrayList<>();
        rule.elementConventions().forEach(elementConvention -> {
            log.info(String.format("Check convention with name %s.", elementConvention.name()));
            var targetElements = BpmnExtractor.extractBpmnElementByTargetElement(process, Element.valueOf(elementConvention.name().toUpperCase()));
            targetElements.forEach(targetElement -> {
                log.info(String.format("Target element found with id %s and name %s.", targetElement.getId(), targetElement.getName()));
                elementConvention.operations().forEach(operation -> {
                    if(!AttributeUtils.isNullOrBlank(targetElement.getName())) {
                        var result = AttributeUtils.performCheckBasedOnOperation(operation, targetElement.getName());
                        log.info(String.format("Operation of type [%s] with value [%s] provided result [%s].", operation.type(), operation.value(), result));
                        if(!result){
                            violations.add(new Violation(rule.name(), elementConvention, operation, targetElement.getId()));
                        }
                    }
                    else {
                        log.info(String.format("Target element with id [%s] does not have a valid attribute name.", targetElement.getId()));
                        violations.add(new Violation(rule.name(), elementConvention, operation, targetElement.getId()));
                    }
                });
            });
        });
        return violations;
    }
}
