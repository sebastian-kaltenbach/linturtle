package de.dsheng.linturtle.domain.model;

import java.util.Collection;

public record ViolationSource(
        int count,
        Collection<Violation> violationCollection
) {
}
