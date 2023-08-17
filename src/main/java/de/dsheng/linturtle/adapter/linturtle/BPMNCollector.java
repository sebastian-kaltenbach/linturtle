package de.dsheng.linturtle.adapter.linturtle;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.apache.maven.plugin.logging.Log;

import de.dsheng.linturtle.domain.model.BpmnProvider;
import de.dsheng.linturtle.utils.BpmnModelMapper;
import lombok.Getter;

public class BPMNCollector {

    private Log log;
    private String sourcePath;

    @Getter
    private List<BpmnProvider> bpmnProviderCollection = new ArrayList<>();
    private Set<String> skipBPMNs;

    public BPMNCollector(String sourcePath, Set<String> skipBPMNs, Log log) {
        this.sourcePath = sourcePath;
        this.skipBPMNs = skipBPMNs;
        this.log = log;
        prepare();
    }

    private void prepare() {
        File dir = new File(this.sourcePath);
        this.bpmnProviderCollection = Stream.of(dir.listFiles(bpmnFilefilter))
            .map(bpmnFile -> new BpmnProvider(bpmnFile.getName(), BpmnModelMapper.transformModelToObject(bpmnFile))).toList();
        
        this.bpmnProviderCollection.stream().map(provider ->
            provider.setProcess(BpmnModelMapper.transformDefinitionToProcess(provider.getDefinition()))).toList();
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
