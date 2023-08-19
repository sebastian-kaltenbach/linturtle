package de.dsheng.linturtle.domain.model.annotation;

import lombok.NonNull;

import java.util.Collection;

public record ElementConvention(
        @NonNull
        String name,
        @NonNull
        String description,
        @NonNull
        Collection<Operation> operations) {
}
