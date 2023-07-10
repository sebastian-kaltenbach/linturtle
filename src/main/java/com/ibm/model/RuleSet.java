package com.ibm.model;

import java.util.ArrayList;
import java.util.List;

import com.ibm.model.rules.BaseRule;
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
}
