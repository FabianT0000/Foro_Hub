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
                .description("API Rest de la aplicación Foro Hub ,en la que se tiene la posibilidad de crear un tema(tópico) de discusión ,recibir respuestas de otros usuarios,esto por medio del solicitudes Http POST,ademas se tiene las funcionalidades de CRUD para los cursos,usuarios,tópicos y respuestas o comentarios. Recuerda que para usar Swagger necesitas loguearte si ya tienes un usuario creado,de lo contrario debes crearlo y por último usar el token para interactuar con toda la API")
                .contact(new Contact()
                        .name("Didier Fabian Torres Rey")
                        .email("tfabiann@gmail.com"))
                .license(new License()
                        .name("SpringBoot 3.3.10 - Java 17.0.12 ")
                        .url("")));
    }
}
