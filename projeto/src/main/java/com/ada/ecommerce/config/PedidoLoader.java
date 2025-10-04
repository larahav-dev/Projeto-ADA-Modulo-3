package com.ada.ecommerce.config;

import com.ada.ecommerce.model.*;
import com.ada.ecommerce.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Cria pedidos automaticamente ao iniciar a aplicação.
 */
@Configuration
public class PedidoLoader {

    @Bean
    public CommandLineRunner gerarPedidos(
            ClienteRepository clienteRepo,
            ProdutoRepository produtoRepo,
            CupomDescontoRepository cupomRepo,
            PedidoRepository pedidoRepo,
            ItemPedidoRepository itemRepo) {

        return args -> {
            // Recupera cliente e produtos
            Cliente cliente = clienteRepo.findAll().stream().findFirst().orElse(null);
            List<Produto> produtos = produtoRepo.findAll();
            CupomDesconto cupom = cupomRepo.findById("DESCONTO10").orElse(null);

            if (cliente == null || produtos.size() < 2 || cupom == null) return;

            // Cria pedido
            Pedido pedido = new Pedido(cliente);
            pedido.setStatus(StatusPedido.AGUARDANDO_PAGAMENTO);
            pedido.setStatusPagamento(StatusPagamento.AGUARDANDO);
            pedidoRepo.save(pedido);

            // Adiciona itens
            ItemPedido item1 = new ItemPedido(produtos.get(0), 1, produtos.get(0).getPrecoBase(), pedido);
            ItemPedido item2 = new ItemPedido(produtos.get(1), 2, produtos.get(1).getPrecoBase(), pedido);
            itemRepo.saveAll(List.of(item1, item2));

            pedido.getItens().addAll(List.of(item1, item2));
            pedido.calcularValorTotal();

            // Aplica cupom
            pedido.aplicarCupom(cupom);
            cupom.setUtilizado(true);
            cupomRepo.save(cupom);

            // Atualiza pedido com valor final
            pedidoRepo.save(pedido);

            System.out.println("Pedido gerado automaticamente para " + cliente.getNome());
        };
    }
}