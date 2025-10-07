package com.ada.ecommerce.repository;

import com.ada.ecommerce.model.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositório responsável pelo acesso aos dados de itens de pedido.
 */
@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {

    /**
     * Busca todos os itens associados a um pedido específico.
     *
     * @param pedidoId identificador do pedido
     * @return lista de itens do pedido
     */
    List<ItemPedido> findByPedidoId(Long pedidoId);
}