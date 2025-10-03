package com.ada.ecommerce.model;
/**
 * Representa os possíveis estados de um pedido no sistema de e-commerce.
 */
public enum StatusPedido {
    /** Pedido criado e ainda não finalizado */
    ABERTO,

    /** Pedido finalizado e aguardando pagamento */
    AGUARDANDO_PAGAMENTO,

    /** Pedido com pagamento confirmado */
    PAGO,

    /** Pedido entregue ao cliente */
    FINALIZADO,

    /** Pedido cancelado por algum motivo */
    CANCELADO
}
