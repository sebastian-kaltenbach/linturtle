package de.dsheng.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RuleResult {
    private final boolean valid;
    private final String targetIdentifier;
}
