package de.dsheng.linturtle.adapter.export;

import com.typesafe.config.Config;
import de.dsheng.linturtle.application.utils.JsonPrettyPrintUtils;
import de.dsheng.linturtle.application.utils.ViolationSourceUtils;
import de.dsheng.linturtle.domain.model.BpmnViolationSource;
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
    public void log(Collection<BpmnViolationSource> bpmnViolationSourceCollection) {
        reportSection();
        bpmnViolationSourceCollection.forEach(bpmnViolationSource -> {
            log.info(String.format("Violations of model %s | (%d)", bpmnViolationSource.processName(),  bpmnViolationSource.checkerViolationSourceCollection().stream().map(checkerViolationSource -> checkerViolationSource.violationCollection().size()).reduce(0, Integer::sum)));
            log.info("-".repeat(50));
            bpmnViolationSource.checkerViolationSourceCollection().forEach(checkerViolationSource -> {
                checkerViolationSource.violationCollection().forEach(this::formatViolation);
            });
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
     * @param bpmnViolationSourceCollection
     */
    @Override
    public void export(Config exportConfig, Collection<BpmnViolationSource> bpmnViolationSourceCollection) throws IOException {
        String exportPath = exportConfig.getString("path");
        Format exportFormat = Format.valueOf(exportConfig.getString("format"));
        Files.createDirectories(Paths.get(exportPath));

        switch (exportFormat) {
            case JSON -> JsonExporter.export(bpmnViolationSourceCollection, exportPath);
            case XML -> XmlExporter.export(bpmnViolationSourceCollection, exportPath);
            case TXT -> TxtExporter.export(bpmnViolationSourceCollection, exportPath);
            case JUNIT -> JUnitExporter.export(bpmnViolationSourceCollection, exportPath);
            default -> throw new IllegalArgumentException("Export format is not applicable.");
        }
    }
}
