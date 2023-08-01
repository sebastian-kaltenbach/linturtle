package de.dsheng.linturtle.model.checker;

import de.dsheng.linturtle.model.BaseRule;

public abstract class BaseChecker {
    
    private BaseRule rule;

    public BaseChecker(BaseRule rule) {
        this.rule = rule;
    }
}
