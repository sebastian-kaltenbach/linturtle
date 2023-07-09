package com.ibm.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.maven.plugin.logging.Log;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.ibm.model.MetaData;
import com.ibm.model.RuleSet;
import com.ibm.model.Violation;
import com.ibm.model.annotation.Rule;
import com.ibm.model.entity.Format;
import com.typesafe.config.Config;

public class ReportController {
    
    private Log log;
    private Config config;
    private Map<BpmnModelInstance, List<Violation>> violationSet;

    public ReportController(Config config, Log log) {
        this.config = config;
        this.log = log;
        log.debug("ReportController initialized!");
    }

    public void execute(Map<BpmnModelInstance, List<Violation>> violationSet) {
        String path = this.config.getString("path");
        Format format = Format.valueOf(this.config.getString("format"));
        try {
            Files.createDirectories(Paths.get(path));
            violationSet.forEach((model, violations) -> {
                String fileName = model.getModel().getModelName() + "." + format.toString().toLowerCase();
                File file = new File(path + "/" + fileName);
                MetaData metaData = new MetaData("Plugin", new Date(), violations.size());

                try {
                    file.createNewFile();
                    if(format == Format.JSON) {
                        parseViolationsToJsonString(metaData, violations, file.getAbsolutePath());
                    }
                    else if(format == Format.XML){
                        parseViolationsToXMLString(metaData, violations, file.getAbsolutePath());
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
        log.debug("ReportController executed!");
    }

    private void parseViolationsToJsonString(MetaData metaData, List<Violation> violations, String path) {
        JSONObject rootObject = new JSONObject();
        rootObject.put("name", metaData.getName());
        rootObject.put("timeStamp", metaData.getTimeStamp().toString());
        rootObject.put("incidents", metaData.getIncidents());

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
        rootObject.put("violations", jsonArray);

        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(path));
            writer.write(rootObject.toString(4));
            writer.close();
        } catch (IOException | JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void parseViolationsToXMLString(MetaData metaData, List<Violation> violations, String path) {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element nameElmnt = doc.createElement("name");
            nameElmnt.setTextContent(metaData.getName());
            Element timeStampElmnt = doc.createElement("timeStamp");
            timeStampElmnt.setTextContent(metaData.getTimeStamp().toString());
            Element incidentsElmnt = doc.createElement("incidents");
            incidentsElmnt.setTextContent(String.valueOf(metaData.getIncidents()));
            doc.appendChild(nameElmnt).appendChild(timeStampElmnt).appendChild(incidentsElmnt);

            Element rootElement = doc.createElement("violations");
            violations.forEach(violation -> {
                var rule = (Rule) violation.getRule().getClass().getAnnotations()[0];
                Element violationElmnt = doc.createElement("violation");

                Element ruleElmnt = doc.createElement("rule");
                ruleElmnt.setTextContent(violation.getRule().getClass().getSimpleName());
                Element severityElmnt = doc.createElement("severity");
                severityElmnt.setTextContent(rule.severity().toString());
                Element targetTypeElmnt = doc.createElement("targetType");
                targetTypeElmnt.setTextContent(rule.targetType().toString());
                Element targetIdElmnt = doc.createElement("targetId");
                targetIdElmnt.setTextContent(violation.getTargetId());

                violationElmnt.appendChild(ruleElmnt);
                violationElmnt.appendChild(severityElmnt);
                violationElmnt.appendChild(targetTypeElmnt);
                violationElmnt.appendChild(targetIdElmnt);

                rootElement.appendChild(violationElmnt);
            });
            doc.appendChild(rootElement);

            //  Print logic
            try (FileOutputStream output =
                     new FileOutputStream(path)) {

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            // pretty print XML
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(output);

            transformer.transform(source, result);

            } catch (IOException | TransformerException e) {
                e.printStackTrace();
            }
            
        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void printResultToConsole(RuleSet ruleSet, RuleSet skippedRuleSet) {
        StringBuilder sb = new StringBuilder();

        //  Active rules
        String header = "Rules (" + ruleSet.getRules().size() + ")";
        log.info("");
        log.info(header);
        
        for(int i =0; i < header.length(); i++) sb.append("-");
        log.info(sb.toString());
        
        ruleSet.getRules().forEach(rule -> {
            Rule ruleAnnotation = rule.getClass().getAnnotation(Rule.class);
            log.info(rule.getClass().getSimpleName() + " - " + ruleAnnotation.severity().toString() + " - " + ruleAnnotation.targetType().toString());
        });

        // Skipped Rules
        log.info("");
        header = "Skipped Rules (" + skippedRuleSet.getRules().size() + ")";
        log.info("");
        log.info(header);
        sb = new StringBuilder();
        for(int i =0; i < header.length(); i++) sb.append("-");
        log.info(sb.toString());
        
        skippedRuleSet.getRules().forEach(rule -> {
            Rule ruleAnnotation = rule.getClass().getAnnotation(Rule.class);
            log.info(rule.getClass().getSimpleName() + " - " + ruleAnnotation.severity().toString() + " - " + ruleAnnotation.targetType().toString());
        });  
        
        //  Rule Violations

        log.info("");
        int violationCount = (int) violationSet.values().stream().map(e -> e.size()).reduce(0, Integer::sum);

        header = "Rule violations (" + violationCount + ")";
        log.info("");
        log.info(header);
        sb = new StringBuilder();
        for(int i =0; i < header.length(); i++) sb.append("-");
        log.info(sb.toString());

        violationSet.forEach((model, violations) -> {
            violations.forEach(violation -> {
                Rule ruleAnnotation = violation.getRule().getClass().getAnnotation(Rule.class);
                log.info(model.getModel().getModelName() + " - " + violation.getRule().getClass().getSimpleName() + " - " + ruleAnnotation.severity().toString() + " - " + ruleAnnotation.targetType().toString());
            });
        });
    }
}
