package de.dsheng.linturtle.model;

import java.util.Map;

import org.omg.spec.bpmn._20100524.model.TProcess;

public abstract class GlobalRule implements BaseRule {
    public abstract Map<String, Boolean> check(TProcess process);
}
