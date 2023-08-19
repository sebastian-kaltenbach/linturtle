package de.dsheng.linturtle.domain.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

public record MetaData(
        @NonNull
        String name,
        @NonNull
        Date timestamp,
        int violations) {
}
