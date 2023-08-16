package de.dsheng.linturtle.model.checker;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.maven.plugin.logging.Log;
import org.omg.spec.bpmn._20100524.model.TProcess;
import org.omg.spec.bpmn._20100524.model.TTask;

import de.dsheng.linturtle.model.RuleSet;
import de.dsheng.linturtle.model.Violation;

import de.dsheng.linturtle.model.entity.Element;
import de.dsheng.linturtle.utils.BpmnExtractor;

public class TaskNamingConventionChecker extends BaseChecker {

    private Log log;

    public TaskNamingConventionChecker(Log log) {
        this.log = log;
    }

    @Override
    public Collection<Violation> check(TProcess process) {
        final Collection<Violation> violations = new ArrayList<>();
        final Collection<?> tasks = BpmnExtractor.extractBpmnElementByTargetElement(process, Element.TASK); 
        tasks.stream().forEach(element ->  {     
            log.info("Element Found: " + element.getClass().getSimpleName());
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
