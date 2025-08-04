package com.challenge.foroHub.domain.answers;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DataNewAnswer(
        Long id,

        @NotNull(message = "{required.text}")
        @Size(min = 25, message = "{invalid.text}")
        String message ,

        @NotNull
        Long topic_id,
        @NotNull
        Long user_id
) {
}
