package com.ada.ecommerce.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * DTO que representa um item dentro de um pedido.
 */
public class ItemPedidoDTO {

    /** Identificador do produto */
    @NotNull(message = "O ID do produto é obrigatório")
    private Long produtoId;

    /** Quantidade do produto no pedido */
    @NotNull(message = "A quantidade é obrigatória")
    @Min(value = 1, message = "A quantidade deve ser no mínimo 1")
    private Integer quantidade;

    /** Preço de venda unitário do produto */
    @NotNull(message = "O preço de venda é obrigatório")
    private BigDecimal precoVenda;

    public ItemPedidoDTO() {}

    public ItemPedidoDTO(Long produtoId, Integer quantidade, BigDecimal precoVenda) {
        this.produtoId = produtoId;
        this.quantidade = quantidade;
        this.precoVenda = precoVenda;
    }

    // Getters e Setters
    public Long getProdutoId() { return produtoId; }
    public void setProdutoId(Long produtoId) { this.produtoId = produtoId; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    public BigDecimal getPrecoVenda() { return precoVenda; }
    public void setPrecoVenda(BigDecimal precoVenda) { this.precoVenda = precoVenda; }
}