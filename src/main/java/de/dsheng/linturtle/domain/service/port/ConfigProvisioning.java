package de.dsheng.linturtle.domain.service.port;

import de.dsheng.linturtle.domain.model.annotation.RuleSet;
import de.dsheng.linturtle.domain.model.config.TXmlRuleSet;
import de.dsheng.linturtle.domain.model.omg.spec.bpmn._20100524.model.TProcess;

import java.io.File;
import java.util.Collection;

@FunctionalInterface
public interface ConfigProvisioning {
    public abstract RuleSet collect(String path);
}
