package com.leonardus.kasadalu.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    @Bean
    OpenAPI openAPI(){
        return new OpenAPI().info(new Info().title("Kasa Da Lu").version("1.0.0")
                .description("\"Kasa da Lu\" is a fictional shop that sells french toast at Christmas." +
                        " Through this API, you can register clients and place orders!"));
    }
}
