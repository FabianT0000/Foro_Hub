package com.challenge.foroHub.controller;

import com.challenge.foroHub.domain.courses.*;
import com.challenge.foroHub.domain.users.DataDetailUser;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/courses")
@SecurityRequirement(name = "bearer-key")
public class CourseController {

    private final CourseRepository courseRepository;

    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Transactional
    @PostMapping
    public ResponseEntity newCourse(@RequestBody @Valid DataNewCourse data) {
        Category category=Category.validCategory(data.category());
        var  course= new Courses(data,category);
        courseRepository.save(course);
        return ResponseEntity.ok(new DataDetailCourse(course));
    }

    @GetMapping
    public ResponseEntity<Page<DataDetailCourse>> list(@PageableDefault(size = 10, sort = {"name"}) Pageable paginacion) {
        var page = courseRepository.findAll(paginacion).map(DataDetailCourse::new);

        return ResponseEntity.ok(page);
    }

    @Transactional
    @PutMapping
    public ResponseEntity update(@RequestBody @Valid DataUpdateCourse data) {
        var course = courseRepository.getReferenceById(data.id());
        course.updateInformation(data);

        return ResponseEntity.ok(new DataDetailCourse(course));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        if (!courseRepository.existsById(id)) {
            return ResponseEntity.notFound().build(); // 404
        }
        courseRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @GetMapping("/{id}")
    public ResponseEntity detailOfCourse(@PathVariable Long id) {
        var course = courseRepository.getReferenceById(id);
        return ResponseEntity.ok(new DataDetailCourse(course));
    }

}
