package com.ibm.model.rules;

import com.ibm.model.RuleResult;

public abstract class BaseRule {
    public abstract RuleResult check(Object OUT);
}
