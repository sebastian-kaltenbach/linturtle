package com.ibm.model;

import java.util.ArrayList;
import java.util.List;

import com.ibm.model.rules.BaseRule;

public class RuleSet {
    private List<BaseRule> rules;

    public RuleSet() {
        rules = new ArrayList<>();
    }

    public List<BaseRule> getRules() {
        return this.rules;
    }
}
