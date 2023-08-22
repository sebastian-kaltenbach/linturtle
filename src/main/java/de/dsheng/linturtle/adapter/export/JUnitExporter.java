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
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

//            <?xml version="1.0" encoding="UTF-8" ?>
//            <testsuites id="20140612_170519" name="New_configuration (14/06/12 17:05:19)" tests="225" failures="1262" time="0.001">
//                  <testsuite id="codereview.cobol.analysisProvider" name="COBOL Code Review" tests="45" failures="17" time="0.001">
//                     <testcase id="codereview.cobol.rules.ProgramIdRule" name="Use a program name that matches the source file name" time="0.001">
//                        <failure message="PROGRAM.cbl:2 Use a program name that matches the source file name" type="WARNING">
//                                WARNING: Use a program name that matches the source file name
//                                Category: COBOL Code Review – Naming Conventions
//                                File: /project/PROGRAM.cbl
//                                Line: 2
//                                        </failure>
//                    </testcase>
//                  </testsuite>
//            </testsuites>

public class JUnitExporter {

    public static void export(Collection<BpmnViolationSource> bpmnViolationSourceCollection, String path) {
        File file = new File(String.format("%s/%s.xml", path, "junit_report"));
        MetaData metaData = new MetaData("Linturtle", new Date(),
                ViolationSourceUtils.extractTotalViolationCount(bpmnViolationSourceCollection));
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = null;
        try {
            docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("testsuites");
            rootElement.setAttribute("id", "Provide ID");
            rootElement.setAttribute("name", String.format("%s (%s)", metaData.name(), new SimpleDateFormat("dd/MM/yy hh:mm:ss").format(metaData.timestamp())));
            rootElement.setAttribute("tests", "No. of Tests");
            rootElement.setAttribute("failures", String.valueOf(metaData.violations()));
            rootElement.setAttribute("time", "Test time");

            bpmnViolationSourceCollection.forEach(bpmnViolationSource -> {
                // TestSuite
                Element testSuiteElmnt = doc.createElement("testsuite");
                testSuiteElmnt.setAttribute("id", bpmnViolationSource.processName());
                testSuiteElmnt.setAttribute("name", "Provide Description");
                testSuiteElmnt.setAttribute("time", String.valueOf(bpmnViolationSource.time()));

                bpmnViolationSource.checkerViolationSourceCollection().forEach(checkerViolationSource -> {
                    // TestCase
                    Element testCaseElmnt = doc.createElement("testcase");
                    testCaseElmnt.setAttribute("id", "Provide ID");
                    testCaseElmnt.setAttribute("name", "name");
                    testCaseElmnt.setAttribute("time", String.valueOf(checkerViolationSource.time()));

                    checkerViolationSource.violationCollection().forEach(violation -> {
                        // Failure
                        Element failureElmnt = doc.createElement("failure");
                        failureElmnt.setAttribute("message", String.format("Element with id %s does not match specified type %s and corresponding value %s", violation.targetId(), violation.operation().type(), violation.operation().value()));
                        failureElmnt.setAttribute("type", "WARNING");
                        failureElmnt.setTextContent("Specify Test content");

                        testCaseElmnt.appendChild(failureElmnt);
                    });
                    testSuiteElmnt.appendChild(testCaseElmnt);
                });
                rootElement.appendChild(testSuiteElmnt);
            });
            doc.appendChild(rootElement);
            persistContent(doc, file.getAbsolutePath());

        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

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
