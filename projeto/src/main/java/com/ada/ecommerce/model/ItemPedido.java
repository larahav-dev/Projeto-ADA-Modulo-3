package com.ada.ecommerce.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

/**
 * Representa um item dentro de um pedido no sistema de e-commerce.
 */
@Entity
@Table(name = "itens_pedido")
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Produto associado ao item.
     */
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    /**
     * Pedido ao qual este item pertence.
     */
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    /**
     * Quantidade do produto neste item.
     */
    @Column(nullable = false)
    private Integer quantidade;

    /**
     * Preço unitário de venda do produto neste item.
     */
    @Column(name = "preco_venda", nullable = false, precision = 10, scale = 2)
    private BigDecimal precoVenda;

    /**
     * Construtor padrão exigido pelo JPA.
     */
    public ItemPedido() {}

    /**
     * Construtor completo para facilitar criação de itens.
     *
     * @param produto produto associado
     * @param quantidade quantidade comprada
     * @param precoVenda preço unitário
     * @param pedido pedido ao qual pertence
     */
    public ItemPedido(Produto produto, Integer quantidade, BigDecimal precoVenda, Pedido pedido) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoVenda = precoVenda;
        this.pedido = pedido;
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(BigDecimal precoVenda) {
        this.precoVenda = precoVenda;
    }

    @Override
    public String toString() {
        return "ItemPedido{" +
                "id=" + id +
                ", produto=" + (produto != null ? produto.getNome() : "null") +
                ", quantidade=" + quantidade +
                ", precoVenda=" + precoVenda +
                '}';
    }
}