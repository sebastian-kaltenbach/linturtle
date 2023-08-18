package de.dsheng.linturtle.domain.model.annotation;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

public record Operation(
        @NonNull
        String type,
        @NonNull
        String value){
}
