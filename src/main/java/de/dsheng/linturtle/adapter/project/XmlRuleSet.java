package de.dsheng.linturtle.adapter.project;

import java.util.Collection;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ruleSet")
public class XmlRuleSet {

    private Collection<XmlRule> rules;

    public XmlRuleSet() {
    }

    public XmlRuleSet(Collection<XmlRule> rules) {
        super();
        this.rules = rules;
    }

    @XmlElement(name = "rule", type = XmlRule.class)
    public Collection<XmlRule> getRules() {
        return rules;
    }

    public void setRules(Collection<XmlRule> rules) {
        this.rules = rules;
    }
}
