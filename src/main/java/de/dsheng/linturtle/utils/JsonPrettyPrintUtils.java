package de.dsheng.linturtle.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import jakarta.json.Json;
import jakarta.json.JsonWriter;
import jakarta.json.stream.JsonGenerator;

public final class JsonPrettyPrintUtils {
    
    private static Map<String, Object> properties = Map.of(
        JsonGenerator.PRETTY_PRINTING, true
    );

    public static JsonWriter getPrettyJsonWriter(String path) {
        JsonWriter jsonWriter = null;
        try {
            jsonWriter = Json.createWriterFactory(properties).createWriter(Objects.requireNonNull(new FileWriter(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Objects.requireNonNull(jsonWriter);
    }
}
