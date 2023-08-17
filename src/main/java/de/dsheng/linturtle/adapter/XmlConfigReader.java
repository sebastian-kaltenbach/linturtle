package de.dsheng.linturtle.adapter;

import java.io.File;
import java.util.Collection;

import javax.xml.parsers.ParserConfigurationException;

import de.dsheng.linturtle.domain.model.config.TXmlRuleSet;
import de.dsheng.linturtle.domain.model.omg.spec.bpmn._20100524.model.TProcess;
import de.dsheng.linturtle.domain.service.ConfigReader;
import de.dsheng.linturtle.domain.service.port.ConfigProcessing;
import de.dsheng.linturtle.domain.service.port.ConfigProvisioning;
import org.apache.maven.plugin.logging.Log;

import de.dsheng.linturtle.domain.model.RuleSet;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

public class XmlConfigReader implements ConfigProvisioning {

    private final Log log;

    private final ConfigProcessing processingService;

    public XmlConfigReader(Log log) {
        this.log = log;
        this.processingService = new ConfigReader();
    }

    @Override
    public TXmlRuleSet collect(String path) {
        var configFile = new File(path);
        this.log.info("Config file found with name | [ " + configFile.getName() + " ]");
        return this.processingService.transform(configFile);
    }
}
