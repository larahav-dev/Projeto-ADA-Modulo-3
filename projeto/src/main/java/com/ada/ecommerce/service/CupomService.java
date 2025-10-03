package com.ada.ecommerce.service;

import com.ada.ecommerce.dto.CupomDTO;
import com.ada.ecommerce.dto.CupomAplicacaoDTO;
import com.ada.ecommerce.model.CupomDesconto;
import com.ada.ecommerce.model.Pedido;
import com.ada.ecommerce.repository.CupomDescontoRepository;
import com.ada.ecommerce.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Serviço responsável pelas operações de negócio relacionadas a cupons de desconto.
 */
@Service
public class CupomService {

    @Autowired
    private CupomDescontoRepository cupomRepo;

    @Autowired
    private PedidoRepository pedidoRepo;

    /**
     * Cria um novo cupom de desconto.
     */
    public CupomDTO criar(CupomDTO dto) {
        CupomDesconto cupom = new CupomDesconto(
                dto.getCodigo(),
                dto.getValorDesconto(),
                dto.isPercentual(),
                dto.getDataExpiracao(),
                false
        );
        cupomRepo.save(cupom);
        return dto;
    }

    /**
     * Lista todos os cupons cadastrados.
     */
    public List<CupomDTO> listar() {
        return cupomRepo.findAll().stream()
                .map(c -> new CupomDTO(
                        c.getCodigo(),
                        c.getValorDesconto(),
                        c.isPercentual(),
                        c.getDataExpiracao(),
                        c.isUtilizado()
                ))
                .collect(Collectors.toList());
    }

    /**
     * Atualiza os dados de um cupom existente.
     */
    public CupomDTO atualizar(String codigo, CupomDTO dto) {
        CupomDesconto cupom = cupomRepo.findById(codigo)
                .orElseThrow(() -> new IllegalArgumentException("Cupom não encontrado"));

        cupom.setValorDesconto(dto.getValorDesconto());
        cupom.setPercentual(dto.isPercentual());
        cupom.setDataExpiracao(dto.getDataExpiracao());
        cupomRepo.save(cupom);

        return dto;
    }

    /**
     * Expira manualmente um cupom.
     */
    public void expirar(String codigo) {
        CupomDesconto cupom = cupomRepo.findById(codigo)
                .orElseThrow(() -> new IllegalArgumentException("Cupom não encontrado"));

        cupom.setUtilizado(true);
        cupomRepo.save(cupom);
    }

    /**
     * Aplica um cupom a um pedido específico.
     */
    public void aplicarCupom(Long pedidoId, CupomAplicacaoDTO dto) {
        Pedido pedido = pedidoRepo.findById(pedidoId)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));

        CupomDesconto cupom = cupomRepo.findById(dto.getCodigoCupom())
                .orElseThrow(() -> new IllegalArgumentException("Cupom não encontrado"));

        pedido.aplicarCupom(cupom);
        cupomRepo.save(cupom);
        pedidoRepo.save(pedido);
    }
}