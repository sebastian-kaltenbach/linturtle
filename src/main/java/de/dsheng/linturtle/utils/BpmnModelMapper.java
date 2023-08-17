package de.dsheng.linturtle.utils;

import java.io.File;
import java.util.Objects;
import de.dsheng.linturtle.domain.model.omg.spec.bpmn._20100524.model.TDefinitions;
import de.dsheng.linturtle.domain.model.omg.spec.bpmn._20100524.model.TProcess;
import jakarta.xml.bind.JAXB;

public final class BpmnModelMapper {
    private BpmnModelMapper(){}

    public static TDefinitions transformModelToObject(File file) {
        return Objects.requireNonNull(JAXB.unmarshal(file, TDefinitions.class));
    }

    public static TProcess transformDefinitionToProcess(TDefinitions definition) {
        return Objects.requireNonNull((TProcess) definition.getRootElement().stream().filter(rootElement -> rootElement.getName().getLocalPart().equals("process")).findFirst().get().getValue());
    }
}
