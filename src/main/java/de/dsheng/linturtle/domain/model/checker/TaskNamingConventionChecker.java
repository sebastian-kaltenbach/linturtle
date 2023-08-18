package de.dsheng.linturtle.domain.model.checker;

import java.util.ArrayList;
import java.util.Collection;

import de.dsheng.linturtle.domain.model.annotation.Rule;
import org.apache.maven.plugin.logging.Log;
import de.dsheng.linturtle.domain.model.omg.spec.bpmn._20100524.model.TProcess;
import de.dsheng.linturtle.application.utils.BpmnExtractor;
import de.dsheng.linturtle.domain.model.Violation;

import de.dsheng.linturtle.domain.model.entity.Element;

public class TaskNamingConventionChecker extends BaseChecker {

    private Log log;

    public TaskNamingConventionChecker(Rule rule, Log log) {
        super(rule);
        this.log = log;
    }

    @Override
    public Collection<Violation> check(TProcess process) {
        log.info(String.format("In checker %s check method.",getClass().getSimpleName()));
        final Collection<Violation> violations = new ArrayList<>();
            rule.elementConventions().forEach(elementConvention -> {
                log.info(String.format("Check convention with name %s.", elementConvention.name()));
                var targetElements = BpmnExtractor.extractBpmnElementByTargetElement(process, Element.valueOf(elementConvention.name().toUpperCase()));
                targetElements.forEach(targetElement -> {
                    log.info(String.format("Target element found with id %s ", targetElement.getId()));
                });
        });

        /*ViolationSet violationes = new ViolationSet();
        rules.getRules().stream().forEach(rule -> {
            BpmnExtractor.extractBpmnElementByTargetElement(process, rule.getClass().getAnnotation(Rule.class).targetType())
            .stream().forEach(element -> {
               var result = rule.check(element); 
               if(result) {
                violationes.addViolationToSet(new Violation(rule, "", null));
               }
            });
        });*/

        return violations;
    }
}
