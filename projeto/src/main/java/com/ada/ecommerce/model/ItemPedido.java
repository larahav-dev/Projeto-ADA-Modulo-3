package com.ada.ecommerce.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Representa um item dentro de um pedido no sistema de e-commerce.
 */
@Getter
@Entity
@Table(name = "itens_pedido")
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Produto associado ao item.
     */
    @Setter
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    /**
     * Pedido ao qual este item pertence.
     */
    @Setter
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    /**
     * Quantidade do produto neste item.
     */
    @Setter
    @Column(nullable = false)
    private Integer quantidade;

    /**
     * Preço unitário de venda do produto neste item.
     */
    @Setter
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