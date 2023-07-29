package de.dsheng.linturtle.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

import de.dsheng.linturtle.model.BaseRule;
import de.dsheng.linturtle.model.ComplexRule;
import de.dsheng.linturtle.model.ElementRule;

public final class RuleMapper {

    public static BaseRule transformClassToComplexRule(Class<?> clazz) {
        BaseRule ruleObject = null;
        try {
             ruleObject = (ComplexRule) clazz.getDeclaredConstructor().newInstance();
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
