package de.dsheng.linturtle.domain.service.port;

import de.dsheng.linturtle.domain.model.omg.spec.bpmn._20100524.model.TProcess;

import java.util.Collection;

@FunctionalInterface
public interface BpmnProvisioning {
    public abstract Collection<TProcess> collect(String path);
}
