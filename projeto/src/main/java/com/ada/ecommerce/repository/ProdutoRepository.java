package com.ada.ecommerce.repository;

import com.ada.ecommerce.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositório responsável pelo acesso aos dados de produtos.
 */
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    /**
     * Busca produtos cujo nome contenha o termo informado, ignorando maiúsculas/minúsculas.
     *
     * @param nome termo de busca parcial
     * @return lista de produtos correspondentes
     */
    List<Produto> findByNomeContainingIgnoreCase(String nome);
}