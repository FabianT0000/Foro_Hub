package com.challenge.foroHub.domain.courses;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public enum Category {
    INTEGRALES,
    DERIVADAS,
    SALUD,
    MOVIMIENTO_RECTILINEO,
    LITERATURA,
    HISTORIA,
    COMIDA,
    JAVA,
    SPRINGBOOT,
    HTML;

    public static Category validCategory(String categoria) {
        try {
            return Category.valueOf(categoria.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Categoría inválida: " + categoria
            );
        }
    }
}
