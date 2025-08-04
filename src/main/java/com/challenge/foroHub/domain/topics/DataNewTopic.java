package com.challenge.foroHub.domain.topics;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


public record DataNewTopic(

        Long id,
        @NotNull(message = "{required.title}")
        @Size(min = 10, message = "{invalid.title}")
        String title ,
        @NotNull(message = "{required.text}")
        @Size(min = 25, message = "{invalid.text}")
        String message ,
        Boolean status,
        @NotNull
        Long user_id,
        @NotNull
        Long course_id
) {
}
