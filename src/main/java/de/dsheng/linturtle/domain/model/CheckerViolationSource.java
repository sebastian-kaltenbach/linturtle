package de.dsheng.linturtle.domain.model;

import lombok.Data;

import java.util.Collection;

public record CheckerViolationSource(
        long time,
        Collection<Violation> violationCollection) {
}
