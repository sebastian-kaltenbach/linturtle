package de.dsheng.linturtle.domain.service;

import de.dsheng.linturtle.domain.model.omg.spec.bpmn._20100524.model.TDefinitions;
import de.dsheng.linturtle.domain.model.omg.spec.bpmn._20100524.model.TProcess;
import de.dsheng.linturtle.domain.service.port.BpmnProcessing;
import jakarta.xml.bind.JAXB;

import java.io.File;
import java.util.Objects;

/**
 * .
 */
public class BpmnReader implements BpmnProcessing {
    @Override
    public TProcess transform(File bpmnModelFile) {
        return Objects.requireNonNull((TProcess) JAXB.unmarshal(bpmnModelFile, TDefinitions.class).getRootElement().stream()
                .filter(element -> element.getValue() instanceof TProcess).findFirst().get().getValue());
    }
}
