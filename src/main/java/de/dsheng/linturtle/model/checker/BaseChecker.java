package de.dsheng.linturtle.model.checker;

import java.util.Collection;

import org.omg.spec.bpmn._20100524.model.TProcess;

import de.dsheng.linturtle.model.RuleSet;
import de.dsheng.linturtle.model.Violation;
import de.dsheng.linturtle.model.ViolationSet;

public abstract class BaseChecker {

    public BaseChecker() {
    }

    public abstract Collection<Violation> check(TProcess process);
}
