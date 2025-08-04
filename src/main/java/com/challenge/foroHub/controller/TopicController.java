package com.challenge.foroHub.controller;


import com.challenge.foroHub.domain.courses.Courses;
import com.challenge.foroHub.domain.topics.*;
import com.challenge.foroHub.domain.users.Users;
import com.challenge.foroHub.services.ValidationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topics")
@SecurityRequirement(name = "bearer-key")
//@SecurityRequirement(name = "bearer-key")
public class TopicController {

    private final TopicRepository topicRepository;
    private final ValidationService validationService;

    public TopicController(TopicRepository topicRepository, ValidationService topicService) {
        this.topicRepository = topicRepository;
        this.validationService = topicService;
    }


    @Transactional
    @PostMapping
    public ResponseEntity newTopic(@RequestBody @Valid DataNewTopic data) {
        Users user = validationService.validationOfUserById(data.user_id());
        Courses course = validationService.validationOfCourseById(data.course_id());
        var topic = new Topics(data, user, course);
        topicRepository.save(topic);
        return ResponseEntity.ok(new DataDetailTopic(topic));
    }

    @GetMapping
    public ResponseEntity<Page<DataDetailTopic>> topicsList(@PageableDefault(size = 10, sort = {"title"}) Pageable paginacion) {
        var page = topicRepository.findAll(paginacion).map(DataDetailTopic::new);
        return ResponseEntity.ok(page);
    }

    @Transactional
    @PutMapping
    public ResponseEntity updateTopic(@RequestBody @Valid DataUpdateTopic data) {
        Courses course = validationService.validationOfCourseById(data.course_id());
        var topic = topicRepository.getReferenceById(data.id());
        topic.updateInformation(data, course);

        return ResponseEntity.ok(new DataDetailTopic(topic));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity deleteTopic(@PathVariable Long id) {
        if (!topicRepository.existsById(id)) {
            return ResponseEntity.notFound().build(); // 404
        }
        topicRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @GetMapping("/{id}/answers")
    public ResponseEntity detailOfTopic(@PathVariable Long id) {
        Topics topic = topicRepository.findById(id).get();

        return ResponseEntity.ok(new DataDetailTopicWithAnswers(topic));
    }


}
