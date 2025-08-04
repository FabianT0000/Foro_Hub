package com.challenge.foroHub.infra.springDoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfiguration {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
                .info(new Info()
                .title("API FORO HUB")
                .description("This API allows you to create discussion topics and receive replies from other users through HTTP POST requests. It also provides full CRUD functionality for courses, users, topics, and replies or answers.\n" +
                        "To use Swagger and test the API, you need to log in if you already have an existing user. Otherwise, you can register first, and then use the generated token to interact with all the endpoints securely.\n")
                .contact(new Contact()
                        .name("Didier Fabian Torres Rey")
                        .email("tfabiann@gmail.com"))
                .license(new License()
                        .name("SpringBoot 3.3.10 - Java 17.0.12 ")
                        .url("")));
    }
}
