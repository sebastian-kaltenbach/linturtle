package de.dsheng.linturtle.adapter.export;

import de.dsheng.linturtle.domain.model.MetaData;
import de.dsheng.linturtle.domain.model.Violation;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class TxtExporter {

    public static void export(MetaData metaData, Collection<Violation> violationCollection, String path) {
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
            throw new RuntimeException("Error while parsing violations to txt output");
        }
    }
}
