package de.dsheng.linturtle.domain.model.checker;

import java.util.Collection;

import de.dsheng.linturtle.domain.model.omg.spec.bpmn._20100524.model.TProcess;

import de.dsheng.linturtle.domain.model.Violation;

public abstract class BaseChecker {

    public BaseChecker() {
    }

    public abstract Collection<Violation> check(TProcess process);
}
