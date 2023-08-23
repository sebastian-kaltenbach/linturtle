package de.dsheng.linturtle.adapter.export;

import de.dsheng.linturtle.application.utils.JsonPrettyPrintUtils;
import de.dsheng.linturtle.application.utils.ViolationSourceUtils;
import de.dsheng.linturtle.domain.model.BpmnViolationSource;
import de.dsheng.linturtle.domain.model.MetaData;
import de.dsheng.linturtle.domain.model.Violation;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

import java.io.File;
import java.util.Collection;
import java.util.Date;

public class JsonExporter {
    public static void export(Collection<BpmnViolationSource> bpmnViolationSourceCollection, String path) {
        bpmnViolationSourceCollection.forEach(bpmnViolationSource -> {
            File file = new File(String.format("%s/%s.json", path, bpmnViolationSource.processName()));
            MetaData metaData = new MetaData("Linturtle", new Date(),
                    ViolationSourceUtils.extractViolationCountByBpmnViolationSource(bpmnViolationSource));

            JsonObjectBuilder rootObjectBuilder = Json.createObjectBuilder()
                    .add("name", metaData.name())
                    .add("timeStamp", metaData.timestamp().toString())
                    .add("violationCount", metaData.violations());

            JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

            ViolationSourceUtils.extractAllViolationsForBpmnModel(bpmnViolationSource).forEach(violation -> {
                JsonObject oObj = Json.createObjectBuilder()
                        .add("type", violation.operation().type())
                        .add("value", violation.operation().value())
                        .build();

                JsonObject ecObj = Json.createObjectBuilder()
                        .add("name", violation.elementConvention().name())
                        .add("description", violation.elementConvention().description())
                        .add("operation", oObj)
                        .build();

                JsonObjectBuilder obj = Json.createObjectBuilder()
                        .add("rule", violation.ruleName())
                        .add("elementConvention", ecObj)
                        .add("targetId", violation.targetId());

                jsonArrayBuilder.add(obj.build());
            });
            rootObjectBuilder.add("violations", jsonArrayBuilder.build());
            var jsonWriter = JsonPrettyPrintUtils.getPrettyJsonWriter(file.getAbsolutePath());
            jsonWriter.writeObject(rootObjectBuilder.build());
            jsonWriter.close();
        });
    }
}
