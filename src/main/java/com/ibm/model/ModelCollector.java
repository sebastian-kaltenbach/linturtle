package com.ibm.model;

import java.io.File;
import java.io.FileFilter;
import java.util.Collections;
import java.util.List;

import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ModelCollector {

    private String sourceFolder;

    private final FileFilter bpmnFilefilter = new FileFilter()
    {
        public boolean accept(File file) {
            if (file.getName().endsWith(".bpmn")) {
                return true;
            }
            return false;
        }
    };
    
    public ModelCollector(String sourceFolder) {
        this.sourceFolder = sourceFolder;
    }

    public List<BpmnModelInstance> collectModelsInSourceFolder() {
        log.info("In collectModelsInSourceFolder()");
        List<BpmnModelInstance> modelInstances = Collections.emptyList();
        File directory = new File(this.sourceFolder);   

        for(File file : directory.listFiles(bpmnFilefilter)) {
            modelInstances.add(Bpmn.readModelFromFile(file));
            log.info(file.getName());
        }
        
        return modelInstances;
    }
}
