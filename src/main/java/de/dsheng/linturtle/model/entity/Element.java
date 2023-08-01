package de.dsheng.linturtle.model.entity;

import org.omg.spec.bpmn._20100524.model.TProcess;

public enum Element {
    PROCESS(TProcess.class),
    TASK(TProcess.class),
    USERTASK(TProcess.class),
    SERVICETASK(TProcess.class),
    SCRIPTTASK(TProcess.class),
    BUSINESSRULETASK(TProcess.class),
    EVENT(TProcess.class),
    STARTEVENT(TProcess.class),
    INTERMEDIATECATCHEVENT(TProcess.class),
    INTERMEDIATETHROWEVENT(TProcess.class),
    ENDEVENT(TProcess.class),
    GATEWAY(TProcess.class),
    EXCLUSIVEGATEWAY(TProcess.class),
    PARALLELGATEWAY(TProcess.class),
    SEQUENCEFLOW(TProcess.class);

    private Class<?> reference;

    private Element(Class<?> reference) {
        this.reference = reference;
    }

    public Class<?> Reference() {
        return this.reference;
    }
}
