package de.dsheng.linturtle.domain.service.port;

import de.dsheng.linturtle.domain.model.annotation.Rule;
import de.dsheng.linturtle.domain.model.checker.BaseChecker;
import de.dsheng.linturtle.domain.model.omg.spec.bpmn._20100524.model.TProcess;

import java.util.Collection;
import java.util.Map;

/**
 * .
 */
@FunctionalInterface
public interface BpmnValidating {

    /**
     * .
     * @param bpmnModelCollection
     * @param checkerCollection
     * @return
     */
    public abstract Collection<?> validate(Collection<TProcess> bpmnModelCollection, Collection<BaseChecker> checkerCollection);
}
