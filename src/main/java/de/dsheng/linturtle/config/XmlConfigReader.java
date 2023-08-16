package de.dsheng.linturtle.config;

import java.io.File;
import java.io.InputStream;
import java.util.stream.Stream;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.maven.plugin.logging.Log;

import de.dsheng.linturtle.model.RuleSet;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

public class XmlConfigReader {

    private Log log;

    public XmlConfigReader(Log log) {
        this.log = log;
    }
    
    public RuleSet read(String filePath, ClassLoader classLoader) throws ParserConfigurationException {
        log.info("Start reading config file {" +  filePath + "}");
        RuleSet linturtleConfig = null;
        try {
            var configFile = new File(filePath);
            log.info(configFile.getName());
            JAXBContext jaxbContext = JAXBContext.newInstance(XmlRuleSet.class);
            final Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            File fRuleSet = new File(filePath);

            if(fRuleSet != null) {
                final XmlRuleSet ruleSet = (XmlRuleSet) jaxbUnmarshaller.unmarshal(fRuleSet);
                log.info(ruleSet.toString());
                //linturtleConfig = 
            }
            else {
                throw new ParserConfigurationException();
            }
        } catch (JAXBException e) {
            throw new ParserConfigurationException();
        }
        
        return linturtleConfig;
    }
}
