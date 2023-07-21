package de.dsheng.linturtle.utils;

import java.io.FileWriter;
import java.util.Map;

import jakarta.json.Json;
import jakarta.json.JsonWriter;
import jakarta.json.stream.JsonGenerator;

public final class JsonPrettyPrintUtils {
    
    static Map<String, Object> properties = Map.of(
        JsonGenerator.PRETTY_PRINTING, true
    );

    public static JsonWriter getPrettyJsonWriter(FileWriter writer) {
        return Json.createWriterFactory(properties).createWriter(writer);
    }
}
