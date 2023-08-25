package de.dsheng.linturtle.domain.model.config;

import java.util.Collection;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ruleSet")
public class TXmlRuleSet {

    private Collection<TXmlRule> rules;

    public TXmlRuleSet() {
    }

    public TXmlRuleSet(Collection<TXmlRule> rules) {
        super();
        this.rules = rules;
    }

    @XmlElement(name = "rule", required = true, type = TXmlRule.class)
    public Collection<TXmlRule> getRules() {
        return rules;
    }

    public void setRules(Collection<TXmlRule> rules) {
        this.rules = rules;
    }
}
