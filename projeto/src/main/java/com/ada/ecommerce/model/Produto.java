package com.ada.ecommerce.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

/**
 * Representa um produto disponível para venda no sistema de e-commerce.
 */
@Entity
@Table(name = "produtos")
public class Produto {

    /** Identificador único do produto. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Nome do produto. */
    @Column(nullable = false, length = 100)
    private String nome;

    /** Descrição detalhada do produto. */
    @Column(length = 255)
    private String descricao;

    /** Preço base do produto, sem descontos. */
    @Column(name = "preco_base", nullable = false, precision = 10, scale = 2)
    private BigDecimal precoBase;

    /** Construtor padrão exigido pelo JPA. */
    public Produto() {}

    /**
     * Construtor completo para facilitar a criação de produtos.
     *
     * @param nome nome do produto
     * @param descricao descrição do produto
     * @param precoBase preço base do produto
     */
    public Produto(String nome, String descricao, BigDecimal precoBase) {
        this.nome = nome;
        this.descricao = descricao;
        this.precoBase = precoBase;
    }

    // Getters e Setters

    public Long getId() { return id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public BigDecimal getPrecoBase() { return precoBase; }
    public void setPrecoBase(BigDecimal precoBase) { this.precoBase = precoBase; }
}