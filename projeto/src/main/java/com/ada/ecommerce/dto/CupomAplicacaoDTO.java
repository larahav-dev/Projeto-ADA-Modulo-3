package com.ada.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO utilizado para aplicar um cupom de desconto a um pedido.
 */
public class CupomAplicacaoDTO {

    /**
     * Código do cupom a ser aplicado.
     */
    @NotBlank(message = "O código do cupom não pode estar em branco")
    private String codigoCupom;

    public CupomAplicacaoDTO() {}

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