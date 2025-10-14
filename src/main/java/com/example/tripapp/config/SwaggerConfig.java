package com.example.tripapp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    /**
     * Configures the custom metadata for the OpenAPI documentation.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Trip Management API")
                        .version("1.0")
                        .description("RESTful API for managing user trips using Spring Boot 3, JPA, and MySQL."));
    }
}