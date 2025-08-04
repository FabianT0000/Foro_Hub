package com.challenge.foroHub.domain.courses;

import jakarta.validation.constraints.NotNull;

public record DataNewCourse(
        Long id,
        @NotNull(message = "{course.required.name}")
        String name,
        @NotNull(message = "{course.required.category}")
        String category
) {
}
