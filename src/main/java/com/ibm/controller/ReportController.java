package com.ibm.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.apache.maven.plugin.logging.Log;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.json.JSONArray;
import org.json.JSONObject;

import com.ibm.model.Violation;
import com.ibm.model.annotation.Rule;
import com.ibm.model.entity.Format;
import com.typesafe.config.Config;

public class ReportController {
    
    private Log log;
    private Config config;
    private Map<BpmnModelInstance, List<Violation>> violationSet;

    public ReportController(Config config, Log log, Map<BpmnModelInstance, List<Violation>> violationSet) {
        this.config = config;
        this.log = log;
        log.info("Size: " + violationSet.size());
        this.violationSet = violationSet;
    }

    public void execute() {
        String path = this.config.getString("path");
        Format format = Format.valueOf(this.config.getString("format"));
        try {
            Files.createDirectories(Paths.get(path));
            violationSet.forEach((model, violations) -> {
                String fileName = model.getModel().getModelName() + "." + format.toString().toLowerCase();
                File file = new File(path + "/" + fileName);
                try {
                    file.createNewFile();
                    if(format == Format.JSON) {
                        BufferedWriter writer = new BufferedWriter(new FileWriter(file.getAbsolutePath()));
                        writer.write(parseViolationsToJsonString(violations));
                        writer.close();
                    }
                    else {

                    }
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        log.info("ReportController executed!");
    }

    private String parseViolationsToJsonString(List<Violation> violations) {
        JSONArray jsonArray = new JSONArray();
        violations.forEach(violation -> {
            var rule = (Rule) violation.getRule().getClass().getAnnotations()[0];

            JSONObject obj = new JSONObject();
            obj.put("rule", violation.getRule().getClass().getSimpleName());
            obj.put("severity", rule.severity().toString());
            obj.put("targetType", rule.targetType().toString());
            obj.put("targetId", violation.getTargetId());

            jsonArray.put(obj);
        });
        return jsonArray.toString(4);
    }
}
