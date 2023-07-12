package de.dsheng.model.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import de.dsheng.model.entity.Element;
import de.dsheng.model.entity.Severity;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Rule {
    Element targetType();
    Severity severity();
    String description();
}
