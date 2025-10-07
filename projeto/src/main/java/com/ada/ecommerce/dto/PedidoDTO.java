package com.ada.ecommerce.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO que representa os dados de um pedido para transferência entre camadas.
 * Utilizado para criação, atualização e visualização de pedidos.
 */
public class PedidoDTO {

    /** Identificador único do pedido. */
    private Long id;

    /** Identificador do cliente associado ao pedido. */
    @NotNull(message = "O ID do cliente é obrigatório")
    private Long clienteId;

    /** Data e hora da criação do pedido. */
    private LocalDateTime dataCriacao;

    /** Valor total do pedido, incluindo descontos. */
    private BigDecimal valorTotal;

    /** Status atual do pedido (ex: ABERTO, PAGO, FINALIZADO). */
    private String status;

    /** Status do pagamento (ex: NAO_INICIADO, AGUARDANDO, PAGO). */
    private String statusPagamento;

    /** Código do cupom de desconto aplicado, se houver. */
    private String cupomCodigo;

    /** Lista de itens que compõem o pedido. */
    @NotNull(message = "A lista de itens não pode ser nula")
    @Valid
    private List<ItemPedidoDTO> itens;

    /** Construtor padrão. */
    public PedidoDTO() {}

    /**
     * Construtor completo para facilitar a criação de instâncias.
     *
     * @param id identificador do pedido
     * @param clienteId identificador do cliente
     * @param dataCriacao data de criação
     * @param valorTotal valor total do pedido
     * @param status status do pedido
     * @param statusPagamento status do pagamento
     * @param cupomCodigo código do cupom aplicado
     * @param itens lista de itens do pedido
     */
    public PedidoDTO(Long id, Long clienteId, LocalDateTime dataCriacao, BigDecimal valorTotal,
                     String status, String statusPagamento, String cupomCodigo, List<ItemPedidoDTO> itens) {
        this.id = id;
        this.clienteId = clienteId;
        this.dataCriacao = dataCriacao;
        this.valorTotal = valorTotal;
        this.status = status;
        this.statusPagamento = statusPagamento;
        this.cupomCodigo = cupomCodigo;
        this.itens = itens;
    }

    // Getters e Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }

    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }

    public BigDecimal getValorTotal() { return valorTotal; }
    public void setValorTotal(BigDecimal valorTotal) { this.valorTotal = valorTotal; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getStatusPagamento() { return statusPagamento; }
    public void setStatusPagamento(String statusPagamento) { this.statusPagamento = statusPagamento; }

    public String getCupomCodigo() { return cupomCodigo; }
    public void setCupomCodigo(String cupomCodigo) { this.cupomCodigo = cupomCodigo; }

    public List<ItemPedidoDTO> getItens() { return itens; }
    public void setItens(List<ItemPedidoDTO> itens) { this.itens = itens; }
}