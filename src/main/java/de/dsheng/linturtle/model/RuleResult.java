package de.dsheng.linturtle.model;

import lombok.Data;

@Data
public class RuleResult {

    private boolean valid;
    private String targetIdentifier;

    public RuleResult(boolean valid, String targetIdentifier) {
        this.valid = valid;
        this.targetIdentifier = targetIdentifier;
    }

    public RuleResult valid(boolean valid) {
        this.valid = valid;
        return this;
    }
}
