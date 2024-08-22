package com.example.tiny_url.infrastructure.rest.config;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Alejo Czombos",
                        email = "alejoczombos@gmail.com",
                        url = "https://github.com/AlejoCzombos"
                ),
                title = "Tiny URL API",
                description = "API para acortar URL's.",
                version = "1.0"
                // license = @License( name = "License")
                // url =  "url license"
        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:8080"
                )
        }
)
public class OpenApiConfig {
}