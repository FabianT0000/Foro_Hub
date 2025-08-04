package com.challenge.foroHub.domain.answers;

import jakarta.validation.constraints.NotNull;

public record DataUpdateAnswer(
        @NotNull
        Long id,
        String message
) {
}
