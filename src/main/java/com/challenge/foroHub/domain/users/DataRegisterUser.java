package com.challenge.foroHub.domain.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record DataRegisterUser(
        @NotBlank(message = "{required.name}")
        String name,
        @NotBlank(message = "{required.email}")
        @Email(message = "{invalid.email}")
        String email,
        @NotBlank(message = "{required.login}")
        @Size(min=5, message = "{invalid.login}")
        String login,
        @NotBlank(message = "{required.password}")
        @Pattern(regexp = "\\d{5,}", message = "{invalid.password}")
        String password

) {
}
