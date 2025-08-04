package com.challenge.foroHub.domain.answers;


import com.challenge.foroHub.domain.topics.Topics;
import com.challenge.foroHub.domain.users.Users;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "answers")
@Entity(name = "Answer")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Answers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topics topic;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime creation_date = LocalDateTime.now().plusSeconds(1);

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    public Answers(@Valid DataNewAnswer data, Topics topic, Users user) {
        this.message = data.message();
        this.topic = topic;
        this.user = user;
    }



    public void updateInformation(@Valid DataUpdateAnswer data) {
        if (data.message() != null) {
            this.message = data.message();
        }
    }
}
