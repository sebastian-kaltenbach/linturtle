package com.ibm.model.rules;

import java.util.ArrayList;
import java.util.List;

import com.ibm.rule.bpmn.Rule;
import com.ibm.rule.bpmn.RuleSet;

import lombok.Data;

@Data
public class BasicRuleSet implements RuleSet{

    private List<Rule> rules;
    
    public BasicRuleSet(List<String> skippedRules) {
        List<String> deltaList = new ArrayList<String>(loadBasicRuleNames());
        deltaList.removeAll(skippedRules);

        deltaList.forEach((e) -> this.rules.add(getRuleFromRuleName(e)));
    }

    private List<String> loadBasicRuleNames() {
        List<String> ruleNames = new ArrayList<>();

        

        return ruleNames;
    }

    private Rule getRuleFromRuleName(String ruleName) {
        Rule rule = null;

        return rule;
    }
}
