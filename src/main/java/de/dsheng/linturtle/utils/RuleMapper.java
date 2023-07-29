package de.dsheng.linturtle.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

import de.dsheng.linturtle.model.BaseRule;
import de.dsheng.linturtle.model.ElementRule;
import de.dsheng.linturtle.model.GlobalRule;

public final class RuleMapper {

    public static BaseRule transformClassToGlobalRule(Class<?> clazz) {
        BaseRule ruleObject = null;
        try {
             ruleObject = (GlobalRule) clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }
        return Objects.requireNonNull(ruleObject);
    }

    public static BaseRule transformClassToElementRule(Class<?> clazz) {
        BaseRule ruleObject = null;
        try {
             ruleObject = (ElementRule) clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }
        return Objects.requireNonNull(ruleObject);
    }
}
