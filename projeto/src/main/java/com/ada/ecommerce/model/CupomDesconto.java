package com.ada.ecommerce.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Representa um cupom de desconto que pode ser aplicado a pedidos.
 */
@Entity
@Table(name = "cupons_desconto")
public class CupomDesconto {

    /** Código único do cupom. */
    @Id
    @Column(name = "codigo", unique = true, nullable = false, length = 50)
    private String codigo;

    /** Valor do desconto oferecido pelo cupom. */
    @Column(name = "valor_desconto", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorDesconto;

    /** Indica se o desconto é percentual (true) ou fixo (false). */
    @Column(name = "percentual", nullable = false)
    private boolean percentual;

    /** Data de expiração do cupom. */
    @Column(name = "data_expiracao", nullable = false)
    private LocalDate dataExpiracao;

    /** Indica se o cupom já foi utilizado. */
    @Column(name = "utilizado", nullable = false)
    private boolean utilizado;

    /** Construtor padrão exigido pelo JPA. */
    public CupomDesconto() {}

    /**
     * Construtor completo para facilitar a criação de cupons.
     *
     * @param codigo código do cupom
     * @param valorDesconto valor do desconto
     * @param percentual se é percentual
     * @param dataExpiracao data de expiração
     * @param utilizado se já foi utilizado
     */
    public CupomDesconto(String codigo, BigDecimal valorDesconto, boolean percentual, LocalDate dataExpiracao, boolean utilizado) {
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

    @Override
    public String toString() {
        return "CupomDesconto{" +
                "codigo='" + codigo + '\'' +
                ", valorDesconto=" + valorDesconto +
                ", percentual=" + percentual +
                ", dataExpiracao=" + dataExpiracao +
                ", utilizado=" + utilizado +
                '}';
    }
}