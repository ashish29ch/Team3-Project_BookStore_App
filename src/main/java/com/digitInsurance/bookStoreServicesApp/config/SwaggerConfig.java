package com.digitInsurance.bookStoreServicesApp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    public OpenAPI customOpenApi(){
        return new OpenAPI()
                .info(new Info()
                        .title("Spring Boot Application for Demo")
                        .version("2.0.0")
                        .description("API documentation for spring VOot Application"));
    }
}
