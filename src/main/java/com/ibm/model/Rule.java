package com.ibm.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.ibm.model.entity.Element;
import com.ibm.model.entity.Severity;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface Rule {
    Element type();
    String target();
    Severity severity();
}
