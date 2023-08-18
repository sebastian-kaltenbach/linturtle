package de.dsheng.linturtle.domain.service;

import de.dsheng.linturtle.domain.model.annotation.RuleSet;
import de.dsheng.linturtle.domain.model.config.TXmlRuleSet;
import de.dsheng.linturtle.domain.model.omg.spec.bpmn._20100524.model.TDefinitions;
import de.dsheng.linturtle.domain.model.omg.spec.bpmn._20100524.model.TProcess;
import de.dsheng.linturtle.domain.service.mapping.ConfigMapper;
import de.dsheng.linturtle.domain.service.port.BpmnProcessing;
import de.dsheng.linturtle.domain.service.port.ConfigProcessing;
import jakarta.xml.bind.JAXB;

import javax.inject.Inject;
import java.io.File;
import java.util.Objects;

/**
 * .
 */
public class ConfigReader implements ConfigProcessing {

    @Override
    public RuleSet transform(File configFile) {
        final var xmlRuleSet = Objects.requireNonNull(JAXB.unmarshal(configFile, TXmlRuleSet.class));
        return ConfigMapper.INSTANCE.dtoToDomain(xmlRuleSet);
    }
}
