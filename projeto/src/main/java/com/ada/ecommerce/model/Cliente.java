package com.ada.ecommerce.model;

import jakarta.persistence.*;
import lombok.Setter;

/**
 * Representa um cliente do sistema de e-commerce.
 */
@Entity
@Table(name = "clientes")
public class Cliente {

    /** Identificador único do cliente. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Nome completo do cliente. */
    @Setter
    @Column(nullable = false, length = 100)
    private String nome;

    /** Documento oficial do cliente (CPF ou CNPJ). */
    @Setter
    @Column(nullable = false, unique = true, length = 14)
    private String documento;

    /** Endereço de e-mail do cliente. */
    @Setter
    @Column(nullable = false, unique = true)
    private String email;

    /** Construtor padrão exigido pelo JPA. */
    public Cliente() {}

    /**
     * Construtor completo para facilitar a criação de clientes.
     *
     * @param nome nome do cliente
     * @param documento CPF ou CNPJ
     * @param email e-mail do cliente
     */
    public Cliente(String nome, String documento, String email) {
        this.nome = nome;
        this.documento = documento;
        this.email = email;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getDocumento() { return documento; }
    public String getEmail() { return email; }
}