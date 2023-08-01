package de.dsheng.linturtle.model.checker;

import org.omg.spec.bpmn._20100524.model.TProcess;

import de.dsheng.linturtle.model.RuleSet;
import de.dsheng.linturtle.model.ViolationSet;

public abstract class ElementChecker {

    protected RuleSet rules;

    public ElementChecker(RuleSet rules) {
        this.rules = rules;
    }

    public abstract ViolationSet check(TProcess process);
}
