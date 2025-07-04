package com.perfulandia.perfulandia.Config;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(
                new Info()
                        .title("API Rest Fullstack I")
                        .version("1.0")
                        .description("Servicios Web Rest Fullstack I 2025")
        );
    }
}
