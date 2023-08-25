package de.dsheng.linturtle.application.utils;

import de.dsheng.linturtle.domain.model.annotation.Operation;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

public class AttributeUtilsTest {

    @Test
    public void isNullOrBlankTest() {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(AttributeUtils.isNullOrBlank(null)).isTrue();
        softAssertions.assertThat(AttributeUtils.isNullOrBlank("")).isTrue();
        softAssertions.assertThat(AttributeUtils.isNullOrBlank("Test")).isFalse();
        softAssertions.assertAll();
    }

    @Test
    public void performCheckBasedOnOperationTest() {
        Operation operationStartsWith = new Operation("startsWith", "Test");
        Operation operationEndsWith = new Operation("endsWith", "Test");
        Operation operationContains = new Operation("contains", "Test");
        Operation operationPattern = new Operation("pattern", "[a-zA-Z]+");
        Operation operationException = new Operation("exception", "Test");


        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(AttributeUtils.performCheckBasedOnOperation(operationStartsWith, "StringTest")).isFalse();
        softAssertions.assertThat(AttributeUtils.performCheckBasedOnOperation(operationStartsWith, "TestString")).isTrue();

        softAssertions.assertThat(AttributeUtils.performCheckBasedOnOperation(operationEndsWith, "StringTest")).isTrue();
        softAssertions.assertThat(AttributeUtils.performCheckBasedOnOperation(operationEndsWith, "TestString")).isFalse();

        softAssertions.assertThat(AttributeUtils.performCheckBasedOnOperation(operationContains, "StringTest")).isTrue();
        softAssertions.assertThat(AttributeUtils.performCheckBasedOnOperation(operationContains, "String")).isFalse();

        softAssertions.assertThat(AttributeUtils.performCheckBasedOnOperation(operationPattern, "Test")).isTrue();
        softAssertions.assertThat(AttributeUtils.performCheckBasedOnOperation(operationPattern, "Test1")).isFalse();

        softAssertions.assertThatIllegalArgumentException().isThrownBy(() -> AttributeUtils.performCheckBasedOnOperation(operationException, "Test"));

        softAssertions.assertAll();
    }
}

