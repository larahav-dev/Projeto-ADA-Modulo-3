package com.ada.ecommerce.model;
/**
 * Representa os possíveis estados do pagamento de um pedido.
 */
public enum StatusPagamento {
    /** Pagamento ainda não iniciado */
    NAO_INICIADO,

    /** Pagamento aguardando confirmação */
    AGUARDANDO,

    /** Pagamento realizado com sucesso */
    PAGO,

    /** Pagamento recusado */
    RECUSADO,

    /** Pagamento estornado após aprovação */
    ESTORNADO
}
