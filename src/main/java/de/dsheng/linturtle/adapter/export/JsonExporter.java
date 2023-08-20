package de.dsheng.linturtle.adapter.export;

import de.dsheng.linturtle.application.utils.JsonPrettyPrintUtils;
import de.dsheng.linturtle.domain.model.MetaData;
import de.dsheng.linturtle.domain.model.Violation;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

import java.util.Collection;

public class JsonExporter {
    public static void export(MetaData metaData, Collection<Violation> violationCollection, String path) {
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
}
