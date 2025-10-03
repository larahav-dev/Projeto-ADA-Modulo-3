package com.ada.ecommerce.controller;

import com.ada.ecommerce.dto.ItemPedidoDTO;
import com.ada.ecommerce.dto.PedidoDTO;
import com.ada.ecommerce.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller responsável pelos endpoints de gerenciamento de pedidos.
 */
@RestController
@RequestMapping("/api/pedidos")
@Tag(name = "Pedidos", description = "Endpoints para gerenciamento de pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    @Operation(summary = "Criar novo pedido", description = "Cria um pedido vazio vinculado a um cliente")
    public ResponseEntity<PedidoDTO> criarPedido(@Valid @RequestBody PedidoDTO dto) {
        PedidoDTO novoPedido = pedidoService.criar(dto);
        return ResponseEntity.status(201).body(novoPedido);
    }

    @PostMapping("/{pedidoId}/itens")
    @Operation(summary = "Adicionar item ao pedido", description = "Adiciona um produto com quantidade e preço ao pedido")
    public ResponseEntity<Void> adicionarItem(@PathVariable Long pedidoId, @Valid @RequestBody ItemPedidoDTO itemDto) {
        pedidoService.adicionarItem(pedidoId, itemDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{pedidoId}/cupom")
    @Operation(summary = "Aplicar cupom ao pedido", description = "Aplica um cupom válido ao pedido e recalcula o valor total")
    public ResponseEntity<Void> aplicarCupom(@PathVariable Long pedidoId, @RequestParam String codigoCupom) {
        pedidoService.aplicarCupom(pedidoId, codigoCupom);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{pedidoId}/finalizar")
    @Operation(summary = "Finalizar pedido", description = "Finaliza o pedido e altera o status para aguardando pagamento")
    public ResponseEntity<Void> finalizarPedido(@PathVariable Long pedidoId) {
        pedidoService.finalizar(pedidoId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{pedidoId}/pagar")
    @Operation(summary = "Registrar pagamento", description = "Atualiza o status de pagamento para PAGO")
    public ResponseEntity<Void> pagarPedido(@PathVariable Long pedidoId) {
        pedidoService.pagar(pedidoId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{pedidoId}/entregar")
    @Operation(summary = "Registrar entrega", description = "Atualiza o status do pedido para FINALIZADO após pagamento")
    public ResponseEntity<Void> entregarPedido(@PathVariable Long pedidoId) {
        pedidoService.entregar(pedidoId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/inicial")
    @Operation(summary = "Criar pedido de exemplo", description = "Gera um pedido automático com cliente, produtos e cupom")
    public ResponseEntity<Void> criarPedidoExemplo() {
        pedidoService.criarPedidoExemplo();
        return ResponseEntity.ok().build();
    }
}