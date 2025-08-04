package com.challenge.foroHub.domain.users;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DataAuthentication(
        @NotBlank(message = "{required.user}")
        @Size(min = 4, message="{invalid.login}")
        String login,
        @NotBlank(message = "{required.password}")
        String password
) {
}
