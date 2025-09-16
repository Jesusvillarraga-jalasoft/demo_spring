package com.tonolandia.demo.conf;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Demo de Spring Data JPA + Swagger UI")
                        .version("1.0.0")
                        .description("""
                    Este proyecto demuestra el uso de **Spring Data JPA**:
                    
                    - Query Methods
                    - @Query con JQL y SQL nativo
                    - Named Queries
                    - EntityGraph (Lazy vs Eager)
                    - Proyecciones (DTO e interface)
                    - Manejo de transacciones con @Transactional
                    - Métodos avanzados de JpaRepository (flush, batch)
                    
                    Incluye documentación automática en Swagger UI.
                    """)
                        .contact(new Contact()
                                .name("Equipo de Backend - Demo Bootcamp")
                                .email("soporte@bootcamp.com")
                                .url("https://bootcamp.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")));
    }
}