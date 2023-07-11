package de.dsheng.model.rules;

import de.dsheng.model.RuleResult;

public abstract class BaseRule {
    public abstract RuleResult check(Object OUT);
}
