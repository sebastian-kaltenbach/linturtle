package de.dsheng.linturtle.controller;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

import org.apache.maven.plugin.logging.Log;
import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;

public class BPMNController {

    private Log log;
    private String sourcePath;
    private List<BpmnModelInstance> bpmnModelInstances = new ArrayList<>();
    private Set<String> skipBPMNs;

    public BPMNController(String sourcePath, Set<String> skipBPMNs, Log log) {
        this.sourcePath = sourcePath;
        this.skipBPMNs = skipBPMNs;
        this.log = log;
    }

    public BPMNController prepare() {
        File dir = new File(this.sourcePath);
        bpmnModelInstances = Stream.of(dir.listFiles(bpmnFilefilter))
            .map(e -> transformBpmnInstanceFromFile(e)).toList();
        printFoundBPMNFiles();
        return this;
    }

    private final FileFilter bpmnFilefilter = new FileFilter()
    {
        public boolean accept(File file) {
            if (file.getName().endsWith(".bpmn") && !skipBPMNs.contains(file.getName())) {
                return true;
            }
            return false;
        }
    };

    private BpmnModelInstance transformBpmnInstanceFromFile(File file) {
        return Objects.requireNonNull(Bpmn.readModelFromFile(file));
    }

    public List<BpmnModelInstance> getBpmnModelInstances() {
        return this.bpmnModelInstances;
    }

    private void printFoundBPMNFiles() {
        log.info("");
        log.info("Recognized BPMN files for further processing:");
        File dir = new File(this.sourcePath);
        Stream.of(dir.listFiles(bpmnFilefilter)).forEach(file -> {
            log.info("\t- " + file.getName());
        });
        log.info("");
    }
}
