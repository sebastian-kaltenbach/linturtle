package de.dsheng.linturtle.domain.model.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import de.dsheng.linturtle.domain.model.entity.Element;
import de.dsheng.linturtle.domain.model.entity.Severity;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Rule {
    Element targetType();
    Severity severity();
    String description();
}
