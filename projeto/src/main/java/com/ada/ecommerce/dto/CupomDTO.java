package com.ada.ecommerce.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO que representa os dados de um cupom de desconto.
 * Utilizado para criação, edição e visualização de cupons.
 */
public class CupomDTO {

    /** Código único do cupom. */
    @NotBlank(message = "O código do cupom é obrigatório")
    private String codigo;

    /** Valor do desconto (percentual ou fixo). */
    @NotNull(message = "O valor do desconto é obrigatório")
    @DecimalMin(value = "0.01", message = "O desconto deve ser maior que zero")
    private BigDecimal valorDesconto;

    /** Indica se o desconto é percentual (true) ou fixo (false). */
    private boolean percentual;

    /** Data de expiração do cupom. */
    @NotNull(message = "A data de expiração é obrigatória")
    @Future(message = "A data de expiração deve ser futura")
    private LocalDate dataExpiracao;

    /** Indica se o cupom já foi utilizado. */
    private boolean utilizado;

    /** Construtor padrão. */
    public CupomDTO() {}

    /**
     * Construtor completo para facilitar a criação de instâncias.
     *
     * @param codigo código do cupom
     * @param valorDesconto valor do desconto
     * @param percentual se é percentual
     * @param dataExpiracao data de expiração
     * @param utilizado se já foi utilizado
     */
    public CupomDTO(String codigo, BigDecimal valorDesconto, boolean percentual, LocalDate dataExpiracao, boolean utilizado) {
        this.codigo = codigo;
        this.valorDesconto = valorDesconto;
        this.percentual = percentual;
        this.dataExpiracao = dataExpiracao;
        this.utilizado = utilizado;
    }

    // Getters e Setters

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public BigDecimal getValorDesconto() { return valorDesconto; }
    public void setValorDesconto(BigDecimal valorDesconto) { this.valorDesconto = valorDesconto; }

    public boolean isPercentual() { return percentual; }
    public void setPercentual(boolean percentual) { this.percentual = percentual; }

    public LocalDate getDataExpiracao() { return dataExpiracao; }
    public void setDataExpiracao(LocalDate dataExpiracao) { this.dataExpiracao = dataExpiracao; }

    public boolean isUtilizado() { return utilizado; }
    public void setUtilizado(boolean utilizado) { this.utilizado = utilizado; }
}