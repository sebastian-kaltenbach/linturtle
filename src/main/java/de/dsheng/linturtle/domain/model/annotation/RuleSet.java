package de.dsheng.linturtle.domain.model.annotation;

import java.util.Collection;
import lombok.NonNull;

public record RuleSet(
        @NonNull
        Collection<Rule> rules) {
}
