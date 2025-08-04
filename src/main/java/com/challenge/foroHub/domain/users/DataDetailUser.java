package com.challenge.foroHub.domain.users;

public record DataDetailUser(Long id,String name,String email,String login,String password) {


    public DataDetailUser(Users user) {
        this(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getLogin(),
                user.getPassword()
        );
    }
}
