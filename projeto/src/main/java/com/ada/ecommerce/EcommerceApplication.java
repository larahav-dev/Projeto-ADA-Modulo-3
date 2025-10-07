package com.ada.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal responsável por iniciar a aplicação de e-commerce.
 */
@SpringBootApplication
public class EcommerceApplication {

    /**
     * Método principal que inicia a aplicação Spring Boot.
     *
     * @param args argumentos de linha de comando
     */
    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }
}