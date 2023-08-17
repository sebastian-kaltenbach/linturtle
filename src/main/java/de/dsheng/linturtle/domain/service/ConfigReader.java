package de.dsheng.linturtle.domain.service;

import de.dsheng.linturtle.domain.model.config.TXmlRuleSet;
import de.dsheng.linturtle.domain.model.omg.spec.bpmn._20100524.model.TDefinitions;
import de.dsheng.linturtle.domain.model.omg.spec.bpmn._20100524.model.TProcess;
import de.dsheng.linturtle.domain.service.port.BpmnProcessing;
import de.dsheng.linturtle.domain.service.port.ConfigProcessing;
import jakarta.xml.bind.JAXB;

import java.io.File;
import java.util.Objects;

/**
 * .
 */
public class ConfigReader implements ConfigProcessing {
    @Override
    public TXmlRuleSet transform(File configFile) {
        return Objects.requireNonNull(JAXB.unmarshal(configFile, TXmlRuleSet.class));
    }
}
