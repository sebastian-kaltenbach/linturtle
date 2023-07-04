package com.ibm.rule.bpmn;

import java.util.List;
import lombok.Data;

@Data
public class RuleSet {
    private List<Rule> rules;
}
