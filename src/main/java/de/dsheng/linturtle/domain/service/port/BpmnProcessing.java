package de.dsheng.linturtle.domain.service.port;

import de.dsheng.linturtle.domain.model.omg.spec.bpmn._20100524.model.TProcess;

import java.io.File;

/**
 * .
 */
@FunctionalInterface
public interface BpmnProcessing {

    /**
     * .
     * @return {@link TProcess}-Instance
     */
    public abstract TProcess transform(File bpmnModelFile);
}
