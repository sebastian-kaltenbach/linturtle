package de.dsheng.linturtle.domain.model.annotation;

import java.util.Collection;

import lombok.Data;

public record Rule(
        String id,
        String name,
        String ruleDescription,
        Collection<ElementConvention> elementConventions) {
}
