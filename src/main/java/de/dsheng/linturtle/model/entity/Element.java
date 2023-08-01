package de.dsheng.linturtle.model.entity;

import org.omg.spec.bpmn._20100524.model.TBusinessRuleTask;
import org.omg.spec.bpmn._20100524.model.TEndEvent;
import org.omg.spec.bpmn._20100524.model.TEvent;
import org.omg.spec.bpmn._20100524.model.TEventBasedGateway;
import org.omg.spec.bpmn._20100524.model.TExclusiveGateway;
import org.omg.spec.bpmn._20100524.model.TGateway;
import org.omg.spec.bpmn._20100524.model.TIntermediateCatchEvent;
import org.omg.spec.bpmn._20100524.model.TIntermediateThrowEvent;
import org.omg.spec.bpmn._20100524.model.TParallelGateway;
import org.omg.spec.bpmn._20100524.model.TProcess;
import org.omg.spec.bpmn._20100524.model.TScriptTask;
import org.omg.spec.bpmn._20100524.model.TSequenceFlow;
import org.omg.spec.bpmn._20100524.model.TServiceTask;
import org.omg.spec.bpmn._20100524.model.TStartEvent;
import org.omg.spec.bpmn._20100524.model.TTask;
import org.omg.spec.bpmn._20100524.model.TUserTask;

public enum Element {
    PROCESS(TProcess.class),
    TASK(TTask.class),
    USERTASK(TUserTask.class),
    SERVICETASK(TServiceTask.class),
    SCRIPTTASK(TScriptTask.class),
    BUSINESSRULETASK(TBusinessRuleTask.class),
    EVENT(TEvent.class),
    STARTEVENT(TStartEvent.class),
    INTERMEDIATECATCHEVENT(TIntermediateCatchEvent.class),
    INTERMEDIATETHROWEVENT(TIntermediateThrowEvent.class),
    ENDEVENT(TEndEvent.class),
    GATEWAY(TGateway.class),
    EXCLUSIVEGATEWAY(TExclusiveGateway.class),
    PARALLELGATEWAY(TParallelGateway.class),
    EVENTBASEDGATEWAY(TEventBasedGateway.class),
    SEQUENCEFLOW(TSequenceFlow.class);

    private Class<?> clazz;

    private Element(Class<?> clazz) {
        this.clazz = clazz;
    }

    public Class<?> Clazz() {
        return this.clazz;
    }
}