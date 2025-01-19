package com.example.web.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        System.out.println("SwaggerConfig is loaded");
        return new OpenAPI()
                .info(new Info()
                        .title("Library Application API")
                        .version("1.0.0")
                        .description("API documentation for the Library Application"));
    }
}
