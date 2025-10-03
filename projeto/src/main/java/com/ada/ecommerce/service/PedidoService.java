package com.ada.ecommerce.service;

import com.ada.ecommerce.dto.ItemPedidoDTO;
import com.ada.ecommerce.dto.PedidoDTO;
import com.ada.ecommerce.model.*;
import com.ada.ecommerce.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Serviço responsável pelas operações de negócio relacionadas a pedidos.
 */
@Service
public class PedidoService {

    @Autowired private PedidoRepository pedidoRepo;
    @Autowired private ClienteRepository clienteRepo;
    @Autowired private ProdutoRepository produtoRepo;
    @Autowired private CupomDescontoRepository cupomRepo;
    @Autowired private EmailService emailService;

    /**
     * Cria um novo pedido vinculado a um cliente.
     */
    public PedidoDTO criar(PedidoDTO dto) {
        Cliente cliente = clienteRepo.findById(dto.getClienteId())
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        Pedido pedido = new Pedido(cliente);
        pedidoRepo.save(pedido);
        dto.setId(pedido.getId());
        return dto;
    }

    /**
     * Adiciona um item ao pedido existente.
     */
    public void adicionarItem(Long pedidoId, ItemPedidoDTO itemDto) {
        Pedido pedido = pedidoRepo.findById(pedidoId)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));

        Produto produto = produtoRepo.findById(itemDto.getProdutoId())
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        ItemPedido item = new ItemPedido(produto, itemDto.getQuantidade(), itemDto.getPrecoVenda(), pedido);
        pedido.getItens().add(item);
        pedido.calcularValorTotal();
        pedidoRepo.save(pedido);
    }

    /**
     * Aplica um cupom de desconto ao pedido.
     */
    public void aplicarCupom(Long pedidoId, String codigoCupom) {
        Pedido pedido = pedidoRepo.findById(pedidoId)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));

        CupomDesconto cupom = cupomRepo.findById(codigoCupom)
                .orElseThrow(() -> new IllegalArgumentException("Cupom não encontrado"));

        pedido.aplicarCupom(cupom);
        cupomRepo.save(cupom);
        pedidoRepo.save(pedido);
    }

    /**
     * Finaliza o pedido e envia notificação de pagamento pendente.
     */
    public void finalizar(Long pedidoId) {
        Pedido pedido = pedidoRepo.findById(pedidoId)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));

        if (pedido.getItens().isEmpty() || pedido.getValorTotal().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalStateException("Pedido inválido para finalização.");
        }

        pedido.setStatus(StatusPedido.AGUARDANDO_PAGAMENTO);
        pedido.setStatusPagamento(StatusPagamento.AGUARDANDO);
        pedidoRepo.save(pedido);

        String html = "<h2>Pedido Finalizado</h2>" +
                "<p>Olá " + pedido.getCliente().getNome() + ",</p>" +
                "<p>Seu pedido #" + pedido.getId() + " foi finalizado e está aguardando pagamento.</p>" +
                "<p>Valor total: <strong>R$ " + pedido.getValorTotal() + "</strong></p>";

        emailService.notificarHtml(pedido.getCliente().getEmail(), "Pedido Finalizado - Aguardando Pagamento", html);
    }

    /**
     * Registra o pagamento do pedido e envia confirmação.
     */
    public void pagar(Long pedidoId) {
        Pedido pedido = pedidoRepo.findById(pedidoId)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));

        if (pedido.getStatusPagamento() != StatusPagamento.AGUARDANDO) {
            throw new IllegalStateException("Pagamento não permitido.");
        }

        pedido.setStatusPagamento(StatusPagamento.PAGO);
        pedido.setStatus(StatusPedido.PAGO);
        pedidoRepo.save(pedido);

        String html = "<h2>Pagamento Confirmado</h2>" +
                "<p>Olá " + pedido.getCliente().getNome() + ",</p>" +
                "<p>Recebemos o pagamento do pedido #" + pedido.getId() + ".</p>" +
                "<p>Valor total: <strong>R$ " + pedido.getValorTotal() + "</strong></p>";

        emailService.notificarHtml(pedido.getCliente().getEmail(), "Pagamento Confirmado", html);
    }

    /**
     * Registra a entrega do pedido e envia notificação.
     */
    public void entregar(Long pedidoId) {
        Pedido pedido = pedidoRepo.findById(pedidoId)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));

        if (pedido.getStatusPagamento() != StatusPagamento.PAGO) {
            throw new IllegalStateException("Entrega não permitida.");
        }

        pedido.setStatus(StatusPedido.FINALIZADO);
        pedidoRepo.save(pedido);

        String html = "<h2>Pedido Entregue</h2>" +
                "<p>Olá " + pedido.getCliente().getNome() + ",</p>" +
                "<p>Seu pedido #" + pedido.getId() + " foi entregue com sucesso!</p>" +
                "<p>Esperamos que você aproveite sua compra.</p>";

        emailService.notificarHtml(pedido.getCliente().getEmail(), "Pedido Entregue", html);
    }

    /**
     * Cria um pedido de exemplo com cliente, produtos e cupom.
     * Usado para testes e demonstrações via endpoint /api/pedidos/inicial.
     */
    public void criarPedidoExemplo() {
        // Seleciona o primeiro cliente cadastrado
        Cliente cliente = clienteRepo.findAll().stream().findFirst()
                .orElseThrow(() -> new IllegalStateException("Nenhum cliente cadastrado"));

        // Seleciona os dois primeiros produtos
        List<Produto> produtos = produtoRepo.findAll();
        if (produtos.size() < 2) throw new IllegalStateException("Produtos insuficientes");

        // Recupera o cupom de exemplo
        CupomDesconto cupom = cupomRepo.findById("DESCONTO10")
                .orElseThrow(() -> new IllegalStateException("Cupom não encontrado"));

        // Cria o pedido
        Pedido pedido = new Pedido(cliente);
        pedido.setStatus(StatusPedido.AGUARDANDO_PAGAMENTO);
        pedido.setStatusPagamento(StatusPagamento.AGUARDANDO);

        // Adiciona itens
        ItemPedido item1 = new ItemPedido(produtos.get(0), 1, produtos.get(0).getPreco(), pedido);
        ItemPedido item2 = new ItemPedido(produtos.get(1), 2, produtos.get(1).getPreco(), pedido);
        pedido.getItens().addAll(List.of(item1, item2));
        pedido.calcularValorTotal();

        // Aplica cupom
        pedido.aplicarCupom(cupom);
        cupom.setUtilizado(true);
        cupomRepo.save(cupom);

        // Salva pedido
        pedidoRepo.save(pedido);

        // Envia e-mail de confirmação
        String html = "<h2>Pedido de Exemplo Criado</h2>" +
                "<p>Olá " + cliente.getNome() + ",</p>" +
                "<p>Seu pedido #" + pedido.getId() + " foi gerado com cupom aplicado.</p>" +
                "<p>Valor total: <strong>R$ " + pedido.getValorTotal() + "</strong></p>";

        emailService.notificarHtml(cliente.getEmail(), "Pedido de Exemplo", html);
    }
}