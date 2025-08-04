package com.challenge.foroHub.controller;

import com.challenge.foroHub.domain.users.DataAuthentication;
import com.challenge.foroHub.domain.users.Users;
import com.challenge.foroHub.infra.security.DataTokenJwt;
import com.challenge.foroHub.infra.security.TokenService;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    private  final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    public AutenticacionController(TokenService tokenService, AuthenticationManager authenticationManager) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }



    @PostMapping
    public ResponseEntity login(@RequestBody @Valid DataAuthentication dataAuthentication) {
        var authenticationToken=new UsernamePasswordAuthenticationToken(dataAuthentication.login(),dataAuthentication.password());
        var autenticacion=authenticationManager.authenticate(authenticationToken);
        var tokenJwt=tokenService.generarToken((Users) autenticacion.getPrincipal());
        return ResponseEntity.ok(new DataTokenJwt(tokenJwt));
    }
}
