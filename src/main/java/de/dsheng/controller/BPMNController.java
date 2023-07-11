package de.dsheng.controller;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Stream;

import org.apache.maven.plugin.logging.Log;
import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;

public class BPMNController {

    private Log log;
    private String sourcePath;
    private List<BpmnModelInstance> bpmnModelInstances = new ArrayList<>();

    public BPMNController(String sourcePath, Log log) {
        this.sourcePath = sourcePath;
        this.log = log;
    }

    public BPMNController prepare() {
        File dir = new File(this.sourcePath);
        bpmnModelInstances = Stream.of(dir.listFiles(bpmnFilefilter))
            .map(e -> transformBpmnInstanceFromFile(e)).toList();
        return this;
    }

    private final FileFilter bpmnFilefilter = new FileFilter()
    {
        public boolean accept(File file) {
            if (file.getName().endsWith(".bpmn")) {
                return true;
            }
            return false;
        }
    };

    private BpmnModelInstance transformBpmnInstanceFromFile(File file) {
        log.info("BPMN-Definition found: " + file.getName());
        return Objects.requireNonNull(Bpmn.readModelFromFile(file));
    }

    public List<BpmnModelInstance> gBpmnModelInstances() {
        return this.bpmnModelInstances;
    }
}
