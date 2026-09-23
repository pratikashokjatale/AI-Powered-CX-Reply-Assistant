package com.pratikjatale.assistant.ai.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI cxReplyAssistantOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("AI-Powered CX Reply Assistant API")
                        .description(
                                "Production-ready REST API for AI-assisted customer experience operations, " +
                                "multi-brand knowledge base retrieval, and intelligent guardrail verification."
                        )
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Pratik Jatale")
                                .email("pratikjatale@example.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://springdoc.org")))
                .servers(List.of(
                        new Server()
                                .url("http://136.114.8.75:8081")
                                .description("Production Server")
                ));
    }
}