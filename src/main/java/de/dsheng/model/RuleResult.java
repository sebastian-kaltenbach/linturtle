package de.dsheng.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RuleResult {
    private final boolean valid;
    private final String targetIdentifier;
}
