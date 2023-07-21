package de.dsheng.linturtle.controller;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.apache.maven.plugin.logging.Log;
import org.omg.spec.bpmn._20100524.model.TDefinitions;
import org.omg.spec.bpmn._20100524.model.TProcess;

import de.dsheng.linturtle.utils.BpmnModelMapper;

public class BPMNController {

    private Log log;
    private String sourcePath;
    private List<TDefinitions> bpmnDefinitions = new ArrayList<>();
    private Set<String> skipBPMNs;

    public BPMNController(String sourcePath, Set<String> skipBPMNs, Log log) {
        this.sourcePath = sourcePath;
        this.skipBPMNs = skipBPMNs;
        this.log = log;
        prepare();
    }

    private void prepare() {
        File dir = new File(this.sourcePath);
        bpmnDefinitions = Stream.of(dir.listFiles(bpmnFilefilter))
            .map(bpmnFile -> BpmnModelMapper.transformModelToObject(bpmnFile)).toList();
        printFoundBPMNFiles();
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

    public List<TProcess> getBpmnModelInstances() {
        return this.bpmnDefinitions.stream().map(definition -> BpmnModelMapper.transformDefinitionToProcess(definition)).toList();
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
