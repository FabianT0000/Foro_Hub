package com.challenge.foroHub.domain.users;

import jakarta.validation.constraints.NotNull;

public record DataUpdateUser(
        @NotNull Long id,
        String name,
        String email,
        String login,
        String password
) {
}
