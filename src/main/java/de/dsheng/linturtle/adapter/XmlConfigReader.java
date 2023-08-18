package de.dsheng.linturtle.adapter;

import java.io.File;

import de.dsheng.linturtle.domain.model.annotation.RuleSet;
import de.dsheng.linturtle.domain.model.config.TXmlRuleSet;
import de.dsheng.linturtle.domain.service.ConfigReader;
import de.dsheng.linturtle.domain.service.port.ConfigProcessing;
import de.dsheng.linturtle.domain.service.port.ConfigProvisioning;
import org.apache.maven.plugin.logging.Log;

public class XmlConfigReader implements ConfigProvisioning {

    private final Log log;

    private final ConfigProcessing processingService;

    public XmlConfigReader(Log log) {
        this.log = log;
        this.processingService = new ConfigReader();
    }

    @Override
    public RuleSet collect(String path) {
        var configFile = new File(path);
        this.log.info("Config file found with name | [ " + configFile.getName() + " ]");
        return this.processingService.transform(configFile);
    }
}
