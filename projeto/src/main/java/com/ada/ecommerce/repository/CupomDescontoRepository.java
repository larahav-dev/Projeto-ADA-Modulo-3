package com.ada.ecommerce.repository;

import com.ada.ecommerce.model.CupomDesconto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório responsável pelo acesso aos dados de cupons de desconto.
 * O identificador do cupom é o próprio código (String).
 */
@Repository
public interface CupomDescontoRepository extends JpaRepository<CupomDesconto, String> {
}