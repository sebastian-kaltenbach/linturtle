package de.dsheng.linturtle.domain.model.annotation;

import lombok.NonNull;

public record ElementConvention(
        @NonNull
        String name,
        @NonNull
        String description,
        @NonNull
        Operation operation) {
}
