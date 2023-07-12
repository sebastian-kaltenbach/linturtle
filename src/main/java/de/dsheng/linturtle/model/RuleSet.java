package de.dsheng.linturtle.model;

import java.util.ArrayList;
import java.util.List;

import de.dsheng.linturtle.model.rules.BaseRule;
import lombok.Data;

@Data
public class RuleSet {
    private List<BaseRule> rules;

    public RuleSet() {
        rules = new ArrayList<>();
    }

    public void addRule(BaseRule rule) {
        this.rules.add(rule);
    }

    public void addRules(List<BaseRule> rules) {
        this.rules.addAll(rules);
    }
}
