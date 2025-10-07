package com.ada.ecommerce.repository;

import com.ada.ecommerce.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositório responsável pelo acesso aos dados de clientes.
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    /**
     * Busca um cliente pelo CPF ou CNPJ.
     *
     * @param documento CPF ou CNPJ
     * @return cliente correspondente, se existir
     */
    Optional<Cliente> findByDocumento(String documento);
}