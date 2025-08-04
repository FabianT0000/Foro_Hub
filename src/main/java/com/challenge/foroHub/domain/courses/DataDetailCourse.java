package com.challenge.foroHub.domain.courses;

public record DataDetailCourse(
        Long id,
        String name,
        Category category
) {
    public DataDetailCourse(Courses course) {
        this(
                course.getId(),
                course.getName(),
                course.getCategory()
        );
    }
}
