package de.dsheng.linturtle.adapter;

import java.io.File;
import java.io.FileFilter;
import java.util.*;
import java.util.stream.Stream;

import de.dsheng.linturtle.domain.model.omg.spec.bpmn._20100524.model.TProcess;
import de.dsheng.linturtle.domain.service.BpmnReader;
import de.dsheng.linturtle.domain.service.port.BpmnProcessing;
import de.dsheng.linturtle.domain.service.port.BpmnProvisioning;
import org.apache.maven.plugin.logging.Log;

import de.dsheng.linturtle.domain.model.BpmnProvider;
import de.dsheng.linturtle.utils.BpmnModelMapper;
import lombok.Getter;

public class BPMNCollector implements BpmnProvisioning {

    private final Log log;

    private final BpmnProcessing processingService;

    public BPMNCollector(Log log) {
        this.log = log;
        this.processingService = new BpmnReader();
    }
    @Override
    public Collection<TProcess> collect(String path) {
        File dir = new File(path);
        final Collection<TProcess> processCollection = new ArrayList<>();
        Stream.of(Objects.requireNonNull(dir.listFiles(bpmnFilefilter))).forEach(bpmnFile -> {
            this.log.info("Bpmn model found with name | [ " + bpmnFile.getName() + " ]");
            processCollection.add(this.processingService.transform(bpmnFile));
        });
        return processCollection;
    }

    private final FileFilter bpmnFilefilter = new FileFilter()
    {
        public boolean accept(File file) {
            return file.getName().endsWith(".bpmn");
        }
    };
}
