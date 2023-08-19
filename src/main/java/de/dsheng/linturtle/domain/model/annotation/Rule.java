package de.dsheng.linturtle.domain.model.annotation;

import java.util.Collection;
import lombok.NonNull;

public record Rule(
        String id,
        @NonNull
        String name,
        @NonNull
        String ruleDescription,
        @NonNull
        Collection<ElementConvention> elementConventions) {
}
