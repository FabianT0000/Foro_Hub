package com.challenge.foroHub.controller;


import com.challenge.foroHub.domain.answers.*;
import com.challenge.foroHub.domain.topics.Topics;
import com.challenge.foroHub.domain.users.Users;
import com.challenge.foroHub.services.ValidationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/answers")
@SecurityRequirement(name = "bearer-key")
public class AnswerController {

    private final AnswerRepository answerRepository;
    private  final ValidationService validationService;
    public AnswerController(AnswerRepository answerRepository, ValidationService validationService) {
        this.answerRepository = answerRepository;
        this.validationService = validationService;
    }

    @Transactional
    @PostMapping
    public ResponseEntity newAnswer(@RequestBody @Valid DataNewAnswer data) {
        Users user= validationService.validationOfUserById(data.user_id());
        Topics topic= validationService.validationOfTopicById(data.topic_id());
        var answer = new Answers(data,topic,user);
        answerRepository.save(answer);
        return ResponseEntity.ok(new DataDetailAnswer(answer));
    }

    @Transactional
    @PutMapping
    public ResponseEntity updateAnswer(@RequestBody @Valid DataUpdateAnswer data) {
        var answer = answerRepository.getReferenceById(data.id());
        answer.updateInformation(data);
        return ResponseEntity.ok(new DataDetailAnswer(answer));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity deleteAnswer(@PathVariable Long id) {
        if (!answerRepository.existsById(id)) {
            return ResponseEntity.notFound().build(); // 404
        }
        answerRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
