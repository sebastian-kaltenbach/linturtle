package com.ibm.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.maven.plugin.logging.Log;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.ServiceTask;
import org.camunda.bpm.model.bpmn.instance.Task;
import org.camunda.bpm.model.bpmn.instance.UserTask;
import org.camunda.bpm.model.xml.type.ModelElementType;

import com.ibm.model.RuleResult;
import com.ibm.model.RuleSet;
import com.ibm.model.Violation;
import com.ibm.model.annotation.Rule;
import com.ibm.model.entity.Element;

import lombok.Getter;

public class RuleSetController {

    private Log log;
    private RuleSet ruleSet;
    private BpmnModelInstance bpmnModelInstance;

    @Getter
    private List<Violation> violations;


    public RuleSetController(RuleSet ruleSet, BpmnModelInstance bpmnModelInstance, Log log) {
        this.log = log;
        this.ruleSet = ruleSet;
        this.bpmnModelInstance = bpmnModelInstance;
        violations = new ArrayList<Violation>();
    }

    public RuleSetController execute() {
        ruleSet.getRules().forEach(rule -> {
            Rule ruleAnnotation = rule.getClass().getAnnotation(Rule.class);

            try {
                bpmnModelInstance.getModelElementsByType(camundaClassesProvider(ruleAnnotation.targetType())).forEach(e -> {
                    RuleResult result = rule.check(e);
                    if(result.isValid()) {
                        violations.add(new Violation(rule, bpmnModelInstance, result.getTargetIdentifier()));
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
            case USERTASK:
                return UserTask.class;
            case SERVICETASK:
                return ServiceTask.class;
            default:
                return ModelElementType.class;
        }
    }
}
