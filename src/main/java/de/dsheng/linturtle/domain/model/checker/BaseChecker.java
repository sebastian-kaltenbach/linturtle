package de.dsheng.linturtle.domain.model.checker;

import java.util.Collection;

import de.dsheng.linturtle.domain.model.ViolationSource;
import de.dsheng.linturtle.domain.model.annotation.Rule;
import de.dsheng.linturtle.domain.model.omg.spec.bpmn._20100524.model.TProcess;

import de.dsheng.linturtle.domain.model.Violation;

public abstract class BaseChecker {

    Rule rule;

    /**
     * .
     * @param rule
     */
    public BaseChecker(Rule rule) {
        this.rule = rule;
    }

    /**
     * .
     * @param process
     * @return
     */
    public abstract ViolationSource check(TProcess process);

}
