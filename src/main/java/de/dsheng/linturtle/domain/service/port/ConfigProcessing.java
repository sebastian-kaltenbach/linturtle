package de.dsheng.linturtle.domain.service.port;

import de.dsheng.linturtle.domain.model.config.TXmlRuleSet;
import de.dsheng.linturtle.domain.model.omg.spec.bpmn._20100524.model.TProcess;

import java.io.File;

/**
 * .
 */
@FunctionalInterface
public interface ConfigProcessing {

    /**
     * .
     * @return {@link TProcess}-Instance
     */
    public abstract TXmlRuleSet transform(File configFile);
}
