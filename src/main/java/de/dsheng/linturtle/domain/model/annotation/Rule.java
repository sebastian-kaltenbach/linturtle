package de.dsheng.linturtle.domain.model.annotation;

import java.util.Collection;
import lombok.NonNull;

public record Rule(
        String id,
        @NonNull
        String name,
        @NonNull
        String description,
        @NonNull
        Collection<ElementConvention> elementConventions) {
}
