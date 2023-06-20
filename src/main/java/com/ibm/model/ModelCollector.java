package com.ibm.model;

import java.io.File;
import java.io.FileFilter;
import java.util.List;

import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;

public class ModelCollector {

    private String sourceFolder;
    private List<BpmnModelInstance> modelInstances;

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

    public void collectModelsInSourceFolder() {
        File directory = new File(this.sourceFolder);   

        for(File file : directory.listFiles(bpmnFilefilter)) {
            modelInstances.add(Bpmn.readModelFromFile(file));
            System.out.println(file.getName());
        }
    }
}
