package de.dsheng.linturtle.application.utils;

import de.dsheng.linturtle.domain.model.annotation.Operation;

import java.util.regex.Pattern;

public class AttributeUtils {

    /**
     * .
     * @param string
     * @return
     */
    public static boolean isNullOrBlank(String string) {
        return string == null || string.isBlank();
    }

    public static boolean performCheckBasedOnOperation(Operation operation, String name) {
        return switch (operation.type().toLowerCase()) {
            case "startswith" -> name.startsWith(operation.value());
            case "endswith" -> name.endsWith(operation.value());
            case "contains" -> name.contains(operation.value());
            case "pattern" -> Pattern.compile(operation.value()).matcher(name).matches();
            default ->
                    throw new IllegalArgumentException(String.format("Operation does not have qualified type [%s]", operation.type()));
        };
    }
}
