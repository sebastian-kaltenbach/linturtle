package de.dsheng.linturtle.utils;

import de.dsheng.linturtle.model.annotation.Rule;
import de.dsheng.linturtle.model.entity.Element;

public final class RuleUtils {
    
    public static Element getTargetTypeByAnnotation(Class<?> clazz) {
        Rule ruleAnnotation = clazz.getClass().getAnnotation(Rule.class);
        return ruleAnnotation.targetType();
    }
}
