package com.challenge.foroHub.domain.courses;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "courses")
@Entity(name = "Course")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;

    public Courses(@Valid DataNewCourse data, Category category) {
        this.id = data.id();
        this.name = data.name();
        this.category = category;
    }

    public void updateInformation(@Valid DataUpdateCourse data) {
        if (data.name() != null) {
            this.name = data.name();
        }
        if (data.category() != null) {
            this.category = data.category();
        }
    }
}
