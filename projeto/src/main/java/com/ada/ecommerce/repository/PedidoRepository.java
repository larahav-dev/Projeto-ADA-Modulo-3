package com.ada.ecommerce.repository;

import com.ada.ecommerce.model.Pedido;
import com.ada.ecommerce.model.StatusPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositório responsável pelo acesso aos dados de pedidos.
 */
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    /**
     * Busca todos os pedidos vinculados a um cliente específico.
     *
     * @param clienteId identificador do cliente
     * @return lista de pedidos do cliente
     */
    List<Pedido> findByClienteId(Long clienteId);

    /**
     * Busca todos os pedidos com um determinado status.
     *
     * @param status status do pedido
     * @return lista de pedidos com o status informado
     */
    List<Pedido> findByStatus(StatusPedido status);
}