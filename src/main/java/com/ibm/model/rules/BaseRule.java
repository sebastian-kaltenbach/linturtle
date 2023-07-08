package com.ibm.model.rules;

import javax.print.attribute.standard.Severity;
import javax.swing.text.html.parser.Element;

public abstract class BaseRule {
    
    public abstract boolean check(Object OUT);
}
