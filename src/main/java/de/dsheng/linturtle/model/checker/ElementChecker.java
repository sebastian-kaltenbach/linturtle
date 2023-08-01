package de.dsheng.linturtle.model.checker;

import de.dsheng.linturtle.model.BaseRule;
import de.dsheng.linturtle.model.ViolationSet;

public interface ElementChecker {
    public ViolationSet check(BaseRule rule);
}
