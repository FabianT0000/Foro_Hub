package com.challenge.foroHub.domain.topics;


import java.time.LocalDateTime;

public record DataDetailTopic(
        Long id,
        String title ,
        String message ,
        LocalDateTime creation_date,
        Boolean status,
        Long user_id,
        Long course_id
) {
    public DataDetailTopic(Topics data) {
        this(
                data.getId(),
                data.getTitle(),
                data.getMessage(),
                data.getCreation_date(),
                data.getStatus(),
                data.getUser().getId(),
                data.getCourse().getId()
        );
    }
}
