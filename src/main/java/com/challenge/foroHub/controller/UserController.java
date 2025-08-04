package com.challenge.foroHub.controller;

import com.challenge.foroHub.domain.users.*;
import com.challenge.foroHub.services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    public UserController(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Transactional
    @PostMapping("/singup")
    public ResponseEntity register(@RequestBody @Valid DataRegisterUser data, UriComponentsBuilder uriComponentsBuilder) {
        var user = new Users(data);
        System.out.println(user);
        userService.registerUser(user);
        var uri = uriComponentsBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new DataDetailUser(user));
    }

    @GetMapping
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<Page<DataDetailUser>> list(@PageableDefault(size = 10, sort = {"name"}) Pageable paginacion) {
        var page = userRepository.findAll(paginacion).map(DataDetailUser::new);

        return ResponseEntity.ok(page);
    }

    @Transactional
    @PutMapping
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity update(@RequestBody @Valid DataUpdateUser data) {
        var user = userRepository.getReferenceById(data.id());
        user.updateInformation(data);

        return ResponseEntity.ok(new DataDetailUser(user));
    }

    @Transactional
    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity delete(@PathVariable Long id) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.notFound().build(); // 404
        }
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @GetMapping("/{id}")
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity detailOfUser(@PathVariable Long id) {
        var user = userRepository.getReferenceById(id);
        return ResponseEntity.ok(new DataDetailUser(user));
    }


}
