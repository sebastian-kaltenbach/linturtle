package de.dsheng.linturtle.utils;

import java.io.IOException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import de.dsheng.linturtle.application.utils.JsonPrettyPrintUtils;

public class JsonPrettyPrintUtilsTest {

    @Test
    public void nullPathThrowsException() {
        // act & assert
        Assertions.assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> JsonPrettyPrintUtils.getPrettyJsonWriter(null));
    }

    @Test
    public void emptyPathThrowsException() throws IOException {
        // act & assert
        Assertions.assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> JsonPrettyPrintUtils.getPrettyJsonWriter(""));
    }

    @Test
    public void filePathIsValid() {
        //  act
        var result = JsonPrettyPrintUtils.getPrettyJsonWriter("src/test/java/resources/output.txt");
        //  assert
        Assertions.assertThatObject(result).isNotNull();
    }
}
