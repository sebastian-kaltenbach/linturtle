package com.ibm.controller;

import java.util.ArrayList;
import java.util.List;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.BaseElement;
import org.camunda.bpm.model.bpmn.instance.FlowElement;
import org.camunda.bpm.model.bpmn.instance.Task;

import com.ibm.model.RuleSet;
import com.ibm.model.Violation;
import com.ibm.model.annotation.Rule;
import com.ibm.model.entity.Element;

public class RuleSetController {
    private RuleSet ruleSet;
    private BpmnModelInstance bpmnModelInstance;
    private List<Violation> violations;
    private static String camundaPackage = "org.camunda.bpm.model.bpmn.instance.";

    public List<Violation> getViolations() {
        return this.violations;
    }

    public RuleSetController(RuleSet ruleSet, BpmnModelInstance bpmnModelInstance) {
        this.ruleSet = ruleSet;
        this.bpmnModelInstance = bpmnModelInstance;
        violations = new ArrayList<Violation>();
    }

    public RuleSetController execute() {
        ruleSet.getRules().forEach(rule -> {
            Rule ruleAnnotation = rule.getClass().getAnnotation(Rule.class);

            try {
                bpmnModelInstance.getModelElementsByType(camundaClassesProvider(ruleAnnotation.targetType())).forEach(e -> {
                    if(! rule.check(((FlowElement) e).getName())) {
                        violations.add(new Violation(rule, bpmnModelInstance, ((BaseElement) e).getId()));
                    }
                });
            } catch(Exception e) {
                e.printStackTrace();
            }
        });
        return this;
    }

    private Class camundaClassesProvider(Element targetType) {
        switch(targetType) {
            case TASK:
                return Task.class;
            default:
                return Task.class;
        }
    }
}
