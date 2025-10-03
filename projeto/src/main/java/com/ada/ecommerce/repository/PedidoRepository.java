package com.ada.ecommerce.repository;

import com.ada.ecommerce.model.Pedido;
import com.ada.ecommerce.model.StatusPedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByClienteId(Long clienteId);
    List<Pedido> findByStatus(StatusPedido status);
}
