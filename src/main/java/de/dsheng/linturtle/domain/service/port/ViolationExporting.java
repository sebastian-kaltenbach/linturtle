package de.dsheng.linturtle.domain.service.port;

import com.typesafe.config.Config;
import de.dsheng.linturtle.domain.model.BpmnViolationSource;

import java.io.IOException;
import java.util.Collection;

/**
 * .
 */
@FunctionalInterface
public interface ViolationExporting {
    /**
     * .
     * @param exportConfig
     * @param violationSetCollection
     */
    public abstract void export(Config exportConfig, Collection<BpmnViolationSource> violationSetCollection) throws IOException;
}
