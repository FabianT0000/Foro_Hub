package com.challenge.foroHub.domain.topics;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DataUpdateTopic(
        @NotNull
        Long id,
        String title ,
        String message ,
        Boolean status,
        Long course_id
) {
}
