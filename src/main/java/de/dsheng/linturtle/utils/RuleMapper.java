package de.dsheng.linturtle.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

import de.dsheng.linturtle.model.rules.BaseRule;

public final class RuleMapper {

    public static BaseRule transformClassToRule(Class<?> clazz) {
        BaseRule ruleObject = null;
        try {
             ruleObject = (BaseRule) clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }
        return Objects.requireNonNull(ruleObject);
    }
}
