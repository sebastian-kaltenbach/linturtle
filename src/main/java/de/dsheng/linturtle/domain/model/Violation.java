package de.dsheng.linturtle.domain.model;

import de.dsheng.linturtle.domain.model.annotation.ElementConvention;
import de.dsheng.linturtle.domain.model.annotation.Operation;
import de.dsheng.linturtle.domain.model.annotation.Rule;
import lombok.NonNull;

public record Violation(
        @NonNull
        String ruleName,
        @NonNull
        ElementConvention elementConvention,
        @NonNull
        Operation operation,
        @NonNull
        String targetId
) {
}
