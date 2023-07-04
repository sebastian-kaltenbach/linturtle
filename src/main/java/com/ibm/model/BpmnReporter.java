package com.ibm.model;

import java.io.File;
import java.io.FileFilter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.Task;
import org.camunda.bpm.model.xml.impl.type.ModelElementTypeImpl;
import org.camunda.bpm.model.xml.type.ModelElementType;

import lombok.Getter;

public class BpmnReporter {

    private String source;

    @Getter
    private List<BpmnModelInstance> modelCollection;

    private final FileFilter bpmnFilefilter = new FileFilter()
    {
        public boolean accept(File file) {
            if (file.getName().endsWith(".bpmn")) {
                return true;
            }
            return false;
        }
    };

    public BpmnReporter(String source) {
        this.source = source;
    }

    public BpmnReporter execute() {
        File dir = new File(this.source);
        modelCollection = Stream.of(dir.listFiles(bpmnFilefilter))
            .map(e -> transformBpmnInstanceFromFile(e)).toList();

        return this;
    }

    private BpmnModelInstance transformBpmnInstanceFromFile(File file) {
        return Objects.requireNonNull(Bpmn.readModelFromFile(file));
    }

    public String prepareBpmnReport(BpmnModelInstance modelInstance) {
        modelInstance.getModelElementsByType(Task.class).stream().forEach(e -> System.out.println(e.getName()));
        return modelInstance.getModel().getModelName();
    }
}
