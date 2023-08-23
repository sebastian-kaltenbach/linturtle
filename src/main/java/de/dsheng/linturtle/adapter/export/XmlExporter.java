package de.dsheng.linturtle.adapter.export;

import de.dsheng.linturtle.application.utils.ViolationSourceUtils;
import de.dsheng.linturtle.domain.model.BpmnViolationSource;
import de.dsheng.linturtle.domain.model.MetaData;
import de.dsheng.linturtle.domain.model.Violation;
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
import java.util.Collection;
import java.util.Date;

public class XmlExporter {
    public static void export(Collection<BpmnViolationSource> bpmnViolationSourceCollection, String path) {
        bpmnViolationSourceCollection.forEach(bpmnViolationSource -> {
            File file = new File(String.format("%s/%s.xml", path, bpmnViolationSource.processName()));
            MetaData metaData = new MetaData("Linturtle", new Date(),
                    ViolationSourceUtils.extractViolationCountByBpmnViolationSource(bpmnViolationSource));
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

                ViolationSourceUtils.extractAllViolationsForBpmnModel(bpmnViolationSource).forEach(violation -> {
                    // Operation
                    Element oElmnt = doc.createElement("operation");
                    oElmnt.setAttribute("type", violation.operation().type());
                    oElmnt.setTextContent(violation.operation().value());

                    // ElementConvention
                    Element ecName = doc.createElement("name");
                    ecName.setTextContent(violation.elementConvention().name());
                    Element ecDescription = doc.createElement("description");
                    ecDescription.setTextContent(violation.elementConvention().description());
                    Element ecElmnt = doc.createElement("elementConvention");
                    ecElmnt.appendChild(ecName);
                    ecElmnt.appendChild(ecDescription);
                    ecElmnt.appendChild(oElmnt);

                    // TargetId
                    Element targetIdElmnt = doc.createElement("targetId");
                    targetIdElmnt.setTextContent(violation.targetId());

                    // Rule
                    Element rnElmnt = doc.createElement("name");
                    rnElmnt.setTextContent(violation.ruleName());
                    Element ruleElmnt = doc.createElement("rule");
                    ruleElmnt.appendChild(rnElmnt);
                    ruleElmnt.appendChild(ecElmnt);
                    ruleElmnt.appendChild(targetIdElmnt);

                    violationsElement.appendChild(ruleElmnt);
                });

                rootElement.appendChild(violationsElement);
                doc.appendChild(rootElement);

                persistContent(doc, file.getAbsolutePath());

            } catch (ParserConfigurationException e) {
                throw new RuntimeException();
            }
        });
    }

    /**
     * .
     * @param doc
     * @param path
     */
    private static void persistContent(Document doc, String path) {
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
            throw new RuntimeException();
        }
    }
}
