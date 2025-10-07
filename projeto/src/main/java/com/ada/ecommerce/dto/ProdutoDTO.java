package com.ada.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * DTO para transferência de dados de produto entre camadas da aplicação.
 * Utilizado para criação, edição e visualização de produtos.
 */
public class ProdutoDTO {

    /** Identificador único do produto. */
    private Long id;

    /** Nome do produto. */
    @NotBlank(message = "O nome do produto é obrigatório")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    private String nome;

    /** Descrição detalhada do produto. */
    @Size(max = 255, message = "A descrição deve ter no máximo 255 caracteres")
    private String descricao;

    /** Preço base do produto, sem descontos. */
    @NotNull(message = "O preço base é obrigatório")
    private BigDecimal precoBase;

    /** Construtor padrão. */
    public ProdutoDTO() {}

    /**
     * Construtor completo para facilitar a criação de instâncias.
     *
     * @param id identificador do produto
     * @param nome nome do produto
     * @param descricao descrição do produto
     * @param precoBase preço base do produto
     */
    public ProdutoDTO(Long id, String nome, String descricao, BigDecimal precoBase) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.precoBase = precoBase;
    }

    // Getters e Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public BigDecimal getPrecoBase() { return precoBase; }
    public void setPrecoBase(BigDecimal precoBase) { this.precoBase = precoBase; }
}