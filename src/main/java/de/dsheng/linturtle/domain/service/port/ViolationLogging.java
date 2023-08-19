package de.dsheng.linturtle.domain.service.port;

import de.dsheng.linturtle.domain.model.ViolationSet;

import java.util.Collection;

/**
 * .
 */
@FunctionalInterface
public interface ViolationLogging {

    public abstract void log(Collection<ViolationSet> violationSetCollection);
}
