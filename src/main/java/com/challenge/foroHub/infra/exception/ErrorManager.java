package com.challenge.foroHub.infra.exception;

import com.challenge.foroHub.validationException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ErrorManager {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity errorManager404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity errorManager400(MethodArgumentNotValidException ex) {
        var errores = ex.getFieldErrors();
        var errorModificado = errores.stream().map(errorDataValidation::new).toList();
        return ResponseEntity.badRequest().body(errorModificado);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity errorManager400(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
/*
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity errorManagerBadCredentials() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity errorManagerAuthentication() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Falla en la autenticación");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity errorManagerAccessDenied() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acceso denegado");
    }
*/
    @ExceptionHandler(Exception.class)
    public ResponseEntity errorManager500(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + ex.getLocalizedMessage());
    }
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handleResponseStatusException400(ResponseStatusException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getReason());
    }

    public record errorDataValidation(
            String campo,
            String mensaje
    ) {
        public errorDataValidation(FieldError error) {
            this(
                    error.getField(),
                    error.getDefaultMessage()
            );
        }

        @ExceptionHandler(validationException.class)
        public ResponseEntity errorManagerValidation(validationException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

}
