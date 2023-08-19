package de.dsheng.linturtle.domain.model;

import lombok.NonNull;

import java.util.Collection;

public record ViolationSet(
        @NonNull
        String processName,
        @NonNull
        Collection<Violation> violations
) {
}
