package com.challenge.foroHub.domain.topics;

import com.challenge.foroHub.domain.answers.Answers;
import com.challenge.foroHub.domain.courses.Courses;
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

@Table(name = "topics")
@Entity(name = "Topic")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title ;
    private String message ;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime creation_date = LocalDateTime.now().plusSeconds(1);
    private Boolean status=true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Courses course;

    @OneToMany(mappedBy = "topic",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Answers> answers;


    public Topics(@Valid DataNewTopic data, Users user, Courses course) {
        this.title=data.title();
        this.message=data.message();
        this.status=data.status();
        this.course=course;
        this.user=user;
    }

    public void updateInformation(@Valid DataUpdateTopic data,Courses course) {
        if (data.title() != null) {
            this.title = data.title();
        }
        if (data.message() != null) {
            this.message = data.message();
        }
        if (data.status() != null) {
            this.status=data.status();
        }
        if (data.course_id() != null) {
            this.course=course;
        }
    }

}
