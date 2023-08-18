package de.dsheng.linturtle.domain.model.annotation;

import java.util.Collection;

import lombok.Data;

public record RuleSet(
        Collection<Rule> rules) {
}
