package com.challenge.foroHub.services;

import com.challenge.foroHub.domain.answers.AnswerRepository;
import com.challenge.foroHub.domain.courses.CourseRepository;
import com.challenge.foroHub.domain.courses.Courses;
import com.challenge.foroHub.domain.topics.TopicRepository;
import com.challenge.foroHub.domain.topics.Topics;
import com.challenge.foroHub.domain.users.UserRepository;
import com.challenge.foroHub.domain.users.Users;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {


    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final TopicRepository topicRepository;
    public ValidationService(UserRepository userRepository, CourseRepository courseRepository, TopicRepository topicRepository) {
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
        this.topicRepository = topicRepository;
    }


    public Users validationOfUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El usuario no se encuentra registrado,realiza primero tú registro"));
    }

    public Courses validationOfCourseById(Long id){
        return courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El curso no esta registrado,valida con un administrador para crearlo"));

    }


    public Topics validationOfTopicById(@NotNull Long id){
        return topicRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El Topico no existe,actualiza y verifica si lo no lo han eliminado"));

    }
}
