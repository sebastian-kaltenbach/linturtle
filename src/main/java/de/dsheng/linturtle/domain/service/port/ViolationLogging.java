package de.dsheng.linturtle.domain.service.port;

import de.dsheng.linturtle.domain.model.BpmnViolationSource;

import java.util.Collection;

/**
 * .
 */
@FunctionalInterface
public interface ViolationLogging {

    public abstract void log(Collection<BpmnViolationSource> violationSetCollection);
}
