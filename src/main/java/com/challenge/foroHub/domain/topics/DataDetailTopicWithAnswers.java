package com.challenge.foroHub.domain.topics;

import com.challenge.foroHub.domain.answers.Answers;
import com.challenge.foroHub.domain.answers.DataDetailAnswer;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public record DataDetailTopicWithAnswers(
        Long id,
        String title,
        String message,
        LocalDateTime creation_date,
        Boolean status,
        Long user_id,
        Long course_id,
        List<DataDetailAnswer> answers
) {
    public DataDetailTopicWithAnswers(Topics data) {
        this(
                data.getId(),
                data.getTitle(),
                data.getMessage(),
                data.getCreation_date(),
                data.getStatus(),
                data.getUser().getId(),
                data.getCourse().getId(),
                data.getAnswers().stream()
                        .map(DataDetailAnswer::new)
                        .toList()

        );
    }

}
