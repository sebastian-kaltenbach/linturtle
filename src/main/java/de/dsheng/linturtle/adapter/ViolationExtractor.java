package de.dsheng.linturtle.adapter;

import com.typesafe.config.Config;
import de.dsheng.linturtle.application.utils.JsonPrettyPrintUtils;
import de.dsheng.linturtle.domain.model.MetaData;
import de.dsheng.linturtle.domain.model.Violation;
import de.dsheng.linturtle.domain.model.ViolationSet;
import de.dsheng.linturtle.domain.model.entity.Format;
import de.dsheng.linturtle.domain.service.port.ViolationExporting;
import de.dsheng.linturtle.domain.service.port.ViolationLogging;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import org.apache.maven.plugin.logging.Log;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class ViolationExtractor implements ViolationLogging, ViolationExporting {

    private final Log log;

    public ViolationExtractor(Log log) {
        this.log = log;
    }

    @Override
    public void log(Collection<ViolationSet> violationSetCollection) {
        reportSection();
        violationSetCollection.forEach(violationSet -> {
            log.info(String.format("Violations of model %s | (%d)", violationSet.processName(), violationSet.violations().size()));
            log.info("-".repeat(50));
            violationSet.violations().forEach(this::formatViolation);
            log.info("");
        });
    }

    private void reportSection() {
        log.info("");
        log.info("********************");
        log.info("* Linturtle Report *");
        log.info("********************");
        log.info("");
    }

    private void formatViolation(Violation violation) {
        log.info(String.format("[%s] | Convention [%s] - [%s] | Operation [%s] <on> [%s]",
                violation.ruleName(), violation.elementConvention().name(),
                violation.elementConvention().description(), violation.operation().type(),
                violation.targetId()));
    }

    /**
     * .
     * @param exportConfig
     * @param violationSetCollection
     */
    @Override
    public void export(Config exportConfig, Collection<ViolationSet> violationSetCollection) throws IOException {
        String exportPath = exportConfig.getString("path");
        Format exportFormat = Format.valueOf(exportConfig.getString("format"));

        try {
            Files.createDirectories(Paths.get(exportPath));
            violationSetCollection.forEach(violationSet -> {
                final String fileName = String.format("%s.%s", violationSet.processName(), exportFormat.toString().toLowerCase());
                File file = new File(String.format("%s/%s", exportPath, fileName));
                MetaData metaData = new MetaData("Linturtle", new Date(), violationSet.violations().size());
                switch (exportFormat) {
                    case JSON -> exportViolationsToJsonFile(metaData, violationSet.violations(), file.getAbsolutePath());
                    case XML -> exportViolationsToXmlFile(metaData, violationSet.violations(), file.getAbsolutePath());
                    case TXT -> exportViolationsToTxtFile(metaData, violationSet.violations(), file.getAbsolutePath());
                    default -> throw new IllegalArgumentException("Export format is not applicable.");
                }
            });
        } catch(Exception ignored){}
    }

    private void exportViolationsToJsonFile(MetaData metaData, Collection<Violation> violationCollection, String path) {
        JsonObjectBuilder rootObjectBuilder = Json.createObjectBuilder()
                .add("name", metaData.name())
                .add("timeStamp", metaData.timestamp().toString())
                .add("violationCount", metaData.violations());

        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

        violationCollection.forEach(violation -> {
            JsonObject ecObj = Json.createObjectBuilder()
                    .add("name", violation.elementConvention().name())
                    .add("description", violation.elementConvention().description())
                    .build();
            JsonObject oObj = Json.createObjectBuilder()
                    .add("type", violation.operation().type())
                    .add("value", violation.operation().value())
                    .build();

            JsonObjectBuilder obj = Json.createObjectBuilder()
                    .add("rule", violation.ruleName())
                    .add("elementConvention", ecObj)
                    .add("operation", oObj)
                    .add("targetId", violation.targetId());

            jsonArrayBuilder.add(obj.build());
        });
        rootObjectBuilder.add("violations", jsonArrayBuilder.build());
        var jsonWriter = JsonPrettyPrintUtils.getPrettyJsonWriter(path);
        jsonWriter.writeObject(rootObjectBuilder.build());
        jsonWriter.close();
    }

    private void exportViolationsToXmlFile(MetaData metaData, Collection<Violation> violationCollection, String path) {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("report");

            Element nameElmnt = doc.createElement("name");
            nameElmnt.setTextContent(metaData.name());
            Element timeStampElmnt = doc.createElement("timeStamp");
            timeStampElmnt.setTextContent(metaData.timestamp().toString());
            Element incidentsElmnt = doc.createElement("violationCount");
            incidentsElmnt.setTextContent(String.valueOf(metaData.violations()));
            rootElement.appendChild(nameElmnt);
            rootElement.appendChild(timeStampElmnt);
            rootElement.appendChild(incidentsElmnt);

            Element violationsElement = doc.createElement("violations");
            violationCollection.forEach(violation -> {
                // ElementConvention
                Element ecName = doc.createElement("name");
                ecName.setTextContent(violation.elementConvention().name());
                Element ecDescription = doc.createElement("description");
                ecDescription.setTextContent(violation.elementConvention().description());
                Element ecElmnt = doc.createElement("elementConvention");
                ecElmnt.appendChild(ecName);
                ecElmnt.appendChild(ecDescription);

                // Operation
                Element oElmnt = doc.createElement("operation");
                oElmnt.setAttribute("type", violation.operation().type());
                oElmnt.setTextContent(violation.operation().value());

                // TargetId
                Element targetIdElmnt = doc.createElement("targetId");
                targetIdElmnt.setTextContent(violation.targetId());

                // Rule
                Element rnElmnt = doc.createElement("name");
                rnElmnt.setTextContent(violation.ruleName());
                Element ruleElmnt = doc.createElement("rule");
                ruleElmnt.appendChild(rnElmnt);
                ruleElmnt.appendChild(ecElmnt);
                ruleElmnt.appendChild(oElmnt);
                ruleElmnt.appendChild(targetIdElmnt);

                violationsElement.appendChild(ruleElmnt);
            });
            rootElement.appendChild(violationsElement);
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

    private void exportViolationsToTxtFile(MetaData metaData, Collection<Violation> violationCollection, String path) {
        try(FileOutputStream outputStream = new FileOutputStream(path)){
            final Collection<String> contentList = new ArrayList<>();
            contentList.add("Name: " + metaData.name());
            contentList.add("Timestamp: " + metaData.timestamp());
            contentList.add("ViolationCount: " + metaData.violations());
            contentList.add("-".repeat(25));

            violationCollection.forEach(violation -> {
                contentList.add(String.format("[%s] | Convention [%s] - [%s] | Operation [%s] <on> [%s]",
                        violation.ruleName(), violation.elementConvention().name(),
                        violation.elementConvention().description(), violation.operation().type(),
                        violation.targetId()));
            });
            var content = String.join("\n", contentList);
            outputStream.write(content.getBytes());
        } catch(IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
