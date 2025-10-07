package com.ada.ecommerce.config;

import com.ada.ecommerce.model.Cliente;
import com.ada.ecommerce.model.CupomDesconto;
import com.ada.ecommerce.model.Produto;
import com.ada.ecommerce.repository.ClienteRepository;
import com.ada.ecommerce.repository.CupomDescontoRepository;
import com.ada.ecommerce.repository.ProdutoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Carrega dados iniciais no banco ao iniciar a aplicação.
 * Útil para testes, demonstrações e desenvolvimento local.
 */
@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner loadData(
            ClienteRepository clienteRepo,
            ProdutoRepository produtoRepo,
            CupomDescontoRepository cupomRepo) {
        return args -> {

            // 👤 Clientes
            clienteRepo.save(new Cliente("Larah Oliveira", "12345678900", "larah@email.com"));
            clienteRepo.save(new Cliente("Carlos Mendes", "98765432100", "carlos@email.com"));

            // 📦 Produtos
            produtoRepo.save(new Produto("Teste", "Notebook Dell", new BigDecimal("4500.00")));
            produtoRepo.save(new Produto("Teste", "Smartphone Samsung", new BigDecimal("3200.00")));
            produtoRepo.save(new Produto("Teste", "Fone Bluetooth", new BigDecimal("299.90")));

            // 🎟️ Cupons de desconto
            cupomRepo.save(new CupomDesconto("DESCONTO10", new BigDecimal("10.00"), true, LocalDate.now().plusDays(30), false));
            cupomRepo.save(new CupomDesconto("FRETEGRATIS", new BigDecimal("50.00"), false, LocalDate.now().plusDays(15), false));
        };
    }
}