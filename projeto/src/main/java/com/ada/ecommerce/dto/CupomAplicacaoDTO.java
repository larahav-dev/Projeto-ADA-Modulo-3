package com.ada.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO utilizado para aplicar um cupom de desconto a um pedido.
 * Deve ser enviado junto ao ID do pedido para aplicação do benefício.
 */
public class CupomAplicacaoDTO {

    /** Código do cupom a ser aplicado. */
    @NotBlank(message = "O código do cupom não pode estar em branco")
    private String codigoCupom;

    /** Construtor padrão. */
    public CupomAplicacaoDTO() {}

    /**
     * Construtor completo para facilitar a criação de instâncias.
     *
     * @param codigoCupom código do cupom de desconto
     */
    public CupomAplicacaoDTO(String codigoCupom) {
        this.codigoCupom = codigoCupom;
    }

    public String getCodigoCupom() {
        return codigoCupom;
    }

    public void setCodigoCupom(String codigoCupom) {
        this.codigoCupom = codigoCupom;
    }
}