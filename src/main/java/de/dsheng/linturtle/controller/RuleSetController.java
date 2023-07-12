package de.dsheng.linturtle.controller;

import org.apache.maven.plugin.logging.Log;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.BusinessRuleTask;
import org.camunda.bpm.model.bpmn.instance.EndEvent;
import org.camunda.bpm.model.bpmn.instance.Event;
import org.camunda.bpm.model.bpmn.instance.ExclusiveGateway;
import org.camunda.bpm.model.bpmn.instance.Gateway;
import org.camunda.bpm.model.bpmn.instance.ParallelGateway;
import org.camunda.bpm.model.bpmn.instance.ScriptTask;
import org.camunda.bpm.model.bpmn.instance.ServiceTask;
import org.camunda.bpm.model.bpmn.instance.StartEvent;
import org.camunda.bpm.model.bpmn.instance.Task;
import org.camunda.bpm.model.bpmn.instance.UserTask;
import org.camunda.bpm.model.xml.type.ModelElementType;

import de.dsheng.linturtle.model.RuleResult;
import de.dsheng.linturtle.model.RuleSet;
import de.dsheng.linturtle.model.Violation;
import de.dsheng.linturtle.model.ViolationSet;
import de.dsheng.linturtle.model.annotation.Rule;
import de.dsheng.linturtle.model.entity.Element;
import lombok.Getter;

public class RuleSetController {

    private Log log;
    private RuleSet ruleSet;
    private BpmnModelInstance bpmnModelInstance;

    @Getter
    private ViolationSet violationSet;


    public RuleSetController(RuleSet ruleSet, BpmnModelInstance bpmnModelInstance, Log log) {
        this.log = log;
        this.ruleSet = ruleSet;
        this.bpmnModelInstance = bpmnModelInstance;
        violationSet = new ViolationSet();
    }

    public RuleSetController execute() {
        ruleSet.getRules().forEach(rule -> {
            Rule ruleAnnotation = rule.getClass().getAnnotation(Rule.class);

            try {
                bpmnModelInstance.getModelElementsByType(camundaClassesProvider(ruleAnnotation.targetType())).forEach(e -> {
                    RuleResult result = rule.check(e);
                    if(!result.isValid()) {
                        violationSet.getViolations().add(new Violation(rule, bpmnModelInstance, result.getTargetIdentifier()));
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
            case SCRIPTTASK:
                return ScriptTask.class;
            case BUSINESSRULETASK:
                return BusinessRuleTask.class;
            case EVENT:
                return Event.class;
            case STARTEVENT:
                return StartEvent.class;
            case ENDEVENT:
                return EndEvent.class;
            case GATEWAY:
                return Gateway.class;
            case EXCLUSIVEGATEWAY:
                return ExclusiveGateway.class;
            case PARALLELGATEWAY:
                return ParallelGateway.class;
            default:
                return ModelElementType.class;
        }
    }
}
