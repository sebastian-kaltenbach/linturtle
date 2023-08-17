package de.dsheng.linturtle.domain.model.entity;

import de.dsheng.linturtle.domain.model.omg.spec.bpmn._20100524.model.TBusinessRuleTask;
import de.dsheng.linturtle.domain.model.omg.spec.bpmn._20100524.model.TEndEvent;
import de.dsheng.linturtle.domain.model.omg.spec.bpmn._20100524.model.TEvent;
import de.dsheng.linturtle.domain.model.omg.spec.bpmn._20100524.model.TEventBasedGateway;
import de.dsheng.linturtle.domain.model.omg.spec.bpmn._20100524.model.TExclusiveGateway;
import de.dsheng.linturtle.domain.model.omg.spec.bpmn._20100524.model.TGateway;
import de.dsheng.linturtle.domain.model.omg.spec.bpmn._20100524.model.TIntermediateCatchEvent;
import de.dsheng.linturtle.domain.model.omg.spec.bpmn._20100524.model.TIntermediateThrowEvent;
import de.dsheng.linturtle.domain.model.omg.spec.bpmn._20100524.model.TParallelGateway;
import de.dsheng.linturtle.domain.model.omg.spec.bpmn._20100524.model.TProcess;
import de.dsheng.linturtle.domain.model.omg.spec.bpmn._20100524.model.TScriptTask;
import de.dsheng.linturtle.domain.model.omg.spec.bpmn._20100524.model.TSequenceFlow;
import de.dsheng.linturtle.domain.model.omg.spec.bpmn._20100524.model.TServiceTask;
import de.dsheng.linturtle.domain.model.omg.spec.bpmn._20100524.model.TStartEvent;
import de.dsheng.linturtle.domain.model.omg.spec.bpmn._20100524.model.TTask;
import de.dsheng.linturtle.domain.model.omg.spec.bpmn._20100524.model.TUserTask;

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