package com.challenge.foroHub.domain.answers;


import java.time.LocalDateTime;

public record DataDetailAnswer(
        Long id,
        String message,
        LocalDateTime creation_date,
        Long topic_id,
        Long user_id
) {
    public DataDetailAnswer(Answers data) {
        this(
                data.getId(),
                data.getMessage(),
                data.getCreation_date(),
                data.getTopic().getId(),
                data.getUser().getId()
        );
    }
}
