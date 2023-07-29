package de.dsheng.linturtle.model;

import org.omg.spec.bpmn._20100524.model.TDefinitions;
import org.omg.spec.bpmn._20100524.model.TProcess;

import lombok.Data;

@Data
public class BpmnProvider {
    private String fileName;
    private TDefinitions definition;
    private TProcess process;

    public BpmnProvider(String fileName, TDefinitions definition) {
        this.fileName = fileName;
        this.definition = definition;
    }

    public BpmnProvider setProcess(TProcess process) {
        this.process = process;
        return this;
    }
}
