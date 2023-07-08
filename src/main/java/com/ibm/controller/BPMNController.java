package com.ibm.controller;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Stream;

import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;

public class BPMNController extends Controller {

    private static final Logger LOG = Logger.getLogger("BPMNController.class");
    private String sourcePath;
    private List<BpmnModelInstance> bpmnModelInstances = new ArrayList<>();

    public BPMNController(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    @Override
    public Controller prepare() {
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
        LOG.info("File found: " + file.getName());
        return Objects.requireNonNull(Bpmn.readModelFromFile(file));
    }

    public List<BpmnModelInstance> gBpmnModelInstances() {
        return this.bpmnModelInstances;
    }
}
