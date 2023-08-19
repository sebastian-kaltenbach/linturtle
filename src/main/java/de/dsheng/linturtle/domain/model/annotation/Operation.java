package de.dsheng.linturtle.domain.model.annotation;

import lombok.NonNull;

public record Operation(
        @NonNull
        String type,
        @NonNull
        String value){
}
