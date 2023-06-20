package com.ibm.model;

import java.io.File;
import java.io.FileFilter;
import java.util.List;

public class ModelCollector {

    private String sourceFolder;
    private List<String> modelPaths;

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
        File[] bpmnFiles = directory.listFiles(bpmnFilefilter);

        for(File file : bpmnFiles) {
            System.out.println(file.getName());
        }
    }
}
