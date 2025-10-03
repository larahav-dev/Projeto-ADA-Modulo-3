package com.ada.ecommerce.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa um pedido realizado por um cliente no sistema de e-commerce.
 */
@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Cliente associado ao pedido. */
    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    /** Lista de itens que compõem o pedido. */
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> itens = new ArrayList<>();

    /** Data e hora em que o pedido foi criado. */
    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    /** Valor total do pedido, calculado com base nos itens e descontos. */
    @Column(name = "valor_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorTotal = BigDecimal.ZERO;

    /** Status atual do pedido. */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPedido status = StatusPedido.ABERTO;

    /** Status atual do pagamento. */
    @Enumerated(EnumType.STRING)
    @Column(name = "status_pagamento", nullable = false)
    private StatusPagamento statusPagamento = StatusPagamento.NAO_INICIADO;

    /** Cupom de desconto aplicado ao pedido, se houver. */
    @ManyToOne
    @JoinColumn(name = "cupom_codigo")
    private CupomDesconto cupom;

    // Construtor padrão
    public Pedido() {
        this.dataCriacao = LocalDateTime.now();
    }

    // Construtor com cliente
    public Pedido(Cliente cliente) {
        this();
        this.cliente = cliente;
    }

    /**
     * Calcula o valor total do pedido com base nos itens.
     */
    public void calcularValorTotal() {
        this.valorTotal = itens.stream()
                .map(item -> item.getPrecoVenda().multiply(BigDecimal.valueOf(item.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Aplica um cupom de desconto ao pedido, se válido.
     */
    public void aplicarCupom(CupomDesconto cupom) {
        if (cupom == null || cupom.isUtilizado() || cupom.getDataExpiracao().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Cupom inválido ou expirado.");
        }

        BigDecimal desconto = cupom.isPercentual()
                ? this.valorTotal.multiply(cupom.getValorDesconto().divide(BigDecimal.valueOf(100)))
                : cupom.getValorDesconto();

        this.valorTotal = this.valorTotal.subtract(desconto).max(BigDecimal.ZERO);
        cupom.setUtilizado(true);
        this.cupom = cupom;
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens != null ? itens : new ArrayList<>();
        calcularValorTotal();
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public StatusPagamento getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(StatusPagamento statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public CupomDesconto getCupom() {
        return cupom;
    }

    public void setCupom(CupomDesconto cupom) {
        this.cupom = cupom;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", cliente=" + cliente +
                ", itens=" + itens +
                ", dataCriacao=" + dataCriacao +
                ", valorTotal=" + valorTotal +
                ", status=" + status +
                ", statusPagamento=" + statusPagamento +
                ", cupom=" + (cupom != null ? cupom.getCodigo() : "nenhum") +
                '}';
    }
}