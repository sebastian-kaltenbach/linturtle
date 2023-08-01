package de.dsheng.linturtle.model.checker;

import org.omg.spec.bpmn._20100524.model.TProcess;

import de.dsheng.linturtle.model.RuleSet;
import de.dsheng.linturtle.model.Violation;
import de.dsheng.linturtle.model.ViolationSet;
import de.dsheng.linturtle.model.annotation.Rule;
import de.dsheng.linturtle.utils.BpmnExtractor;

public class CommonChecker extends ElementChecker {

    public CommonChecker(RuleSet rules) {
        super(rules);
    }

    @Override
    public ViolationSet check(TProcess process) {
        ViolationSet violationes = new ViolationSet();
        rules.getRules().stream().forEach(rule -> {
            BpmnExtractor.extractBpmnElementByTargetElement(process, rule.getClass().getAnnotation(Rule.class).targetType())
            .stream().forEach(element -> {
               var result = rule.check(element); 
               if(result) {
                violationes.addViolationToSet(new Violation(rule, "", null));
               }
            });
        });

        return violationes;
    }
}
