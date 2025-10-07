package com.ada.ecommerce.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuração do Swagger/OpenAPI para documentação da API REST.
 */
@Configuration
public class SwaggerConfig {

    /**
     * Define as informações principais da documentação da API.
     *
     * @return instância configurada do OpenAPI
     */
    @Bean
    public OpenAPI ecommerceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("E-Commerce API")
                        .description("Documentação da API de E-Commerce da Ada Tech")
                        .version("v1.0"));
    }
}