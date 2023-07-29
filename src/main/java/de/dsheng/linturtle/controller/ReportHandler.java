package de.dsheng.linturtle.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
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
import org.omg.spec.bpmn._20100524.model.TProcess;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.typesafe.config.Config;

import de.dsheng.linturtle.model.MetaData;
import de.dsheng.linturtle.model.RuleSet;
import de.dsheng.linturtle.model.Violation;
import de.dsheng.linturtle.model.ViolationSet;
import de.dsheng.linturtle.model.annotation.Rule;
import de.dsheng.linturtle.model.entity.Format;
import de.dsheng.linturtle.utils.JsonPrettyPrintUtils;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObjectBuilder;

public class ReportHandler {
    
    private Log log;
    private Config config;

    public ReportHandler(Config config, Log log) {
        this.config = config;
        this.log = log;
        log.debug("ReportController initialized!");
    }

    public void execute(Map<TProcess, ViolationSet> violationSets) {
        String path = this.config.getString("path");
        Format format = Format.valueOf(this.config.getString("format"));
        try {
            Files.createDirectories(Paths.get(path));
            violationSets.forEach((model, violationSet) -> {
                String fileName = model.getName() + "." + format.toString().toLowerCase();
                File file = new File(path + "/" + fileName);
                MetaData metaData = new MetaData("Plugin", new Date(), violationSet.getViolations().size());

                try {
                    file.createNewFile();
                    switch(format) {
                        case JSON:
                            exportViolationsToJsonFile(metaData, violationSet.getViolations(), file.getAbsolutePath());
                            break;
                        case XML:
                            exportViolationsToXmlFile(metaData, violationSet.getViolations(), file.getAbsolutePath());
                            break;
                        case TXT:
                            exportViolationsToTxtFile(metaData, violationSet.getViolations(), file.getAbsolutePath());
                            break;
                        default:
                            break;
                    }
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            });
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        log.debug("ReportController executed!");
    }

    private void exportViolationsToJsonFile(MetaData metaData, List<Violation> violations, String path) {
        JsonObjectBuilder rootObjectBuilder = Json.createObjectBuilder()
        .add("name", metaData.getName())
        .add("timeStamp", metaData.getTimeStamp().toString())
        .add("incidents", metaData.getIncidents());

        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

        violations.forEach(violation -> {
            var rule = (Rule) violation.getRule().getClass().getAnnotations()[0];

            JsonObjectBuilder obj = Json.createObjectBuilder()
            .add("rule", violation.getRule().getClass().getSimpleName())
            .add("severity", rule.severity().toString())
            .add("targetType", rule.targetType().toString())
            .add("targetId", violation.getTargetId());

            jsonArrayBuilder.add(obj.build());
        });
        rootObjectBuilder.add("violations", jsonArrayBuilder.build());
        var jsonWriter = JsonPrettyPrintUtils.getPrettyJsonWriter(path);
        jsonWriter.writeObject(rootObjectBuilder.build());
        jsonWriter.close();
    }

    private void exportViolationsToXmlFile(MetaData metaData, List<Violation> violations, String path) {
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
            try (FileOutputStream output = new FileOutputStream(path)) {
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();

                // pretty print XML
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");

                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(output);

                transformer.transform(source, result);
            } catch (IOException | TransformerException e) {
                log.error(e.getMessage(), e);
            }         
        } catch (ParserConfigurationException e) {
            log.error(e.getMessage(), e);
        }
    }

    private void exportViolationsToTxtFile(MetaData metaData, List<Violation> violations, String path) {
        try(FileOutputStream outputStream = new FileOutputStream(path)){
            final List<String> contentList = new ArrayList<>();
            violations.forEach(violation -> {
                var rule = (Rule) violation.getRule().getClass().getAnnotations()[0];
                contentList.add(violation.getRule().getClass().getSimpleName() + " - " + rule.severity() + " - " + 
                    violation.getTargetId() + " - " + rule.description());
            });
            var content = String.join("\n", contentList);
            outputStream.write(content.getBytes());
        } catch(IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void printResultToConsole(RuleSet ruleSet, RuleSet customRuleSet, RuleSet skippedRuleSet, Map<TProcess, ViolationSet> violationSets) {
        StringBuilder sb = new StringBuilder();
        
        //  Rule Violations
        int violationCount = (int) violationSets.values().stream().map(e -> e.getViolations().size()).reduce(0, Integer::sum);

        String header = "Rule violations (" + violationCount + ")";
        log.info("");
        log.info(header);
        sb = new StringBuilder();
        for(int i =0; i < header.length(); i++) sb.append("-");
        log.info(sb.toString());

        violationSets.forEach((model, violations) -> {
            violations.getViolations().forEach(violation -> {
                Rule ruleAnnotation = violation.getRule().getClass().getAnnotation(Rule.class);
                log.info("\t- " + model.getName() + " - " + 
                violation.getRule().getClass().getSimpleName() + " - " + 
                ruleAnnotation.severity().toString() + " - " + ruleAnnotation.targetType().toString() + " - " +
                violation.getTargetId());
            });
        });
    }
}
