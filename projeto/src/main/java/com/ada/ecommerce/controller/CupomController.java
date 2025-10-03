package com.ada.ecommerce.controller;

import com.ada.ecommerce.dto.CupomDTO;
import com.ada.ecommerce.dto.CupomAplicacaoDTO;
import com.ada.ecommerce.service.CupomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller responsável pelos endpoints de gerenciamento de cupons de desconto.
 */
@RestController
@RequestMapping("/api/cupons")
@Tag(name = "Cupons", description = "Endpoints para gerenciamento e aplicação de cupons de desconto")
public class CupomController {

    @Autowired
    private CupomService cupomService;

    /**
     * Cria um novo cupom de desconto.
     */
    @PostMapping
    @Operation(summary = "Criar cupom", description = "Cria um novo cupom de desconto com código, valor e validade")
    public ResponseEntity<CupomDTO> criar(@Valid @RequestBody CupomDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cupomService.criar(dto));
    }

    /**
     * Lista todos os cupons cadastrados.
     */
    @GetMapping
    @Operation(summary = "Listar cupons", description = "Retorna todos os cupons cadastrados no sistema")
    public ResponseEntity<List<CupomDTO>> listar() {
        return ResponseEntity.ok(cupomService.listar());
    }

    /**
     * Atualiza os dados de um cupom existente.
     */
    @PutMapping("/{codigo}")
    @Operation(summary = "Atualizar cupom", description = "Atualiza os dados de um cupom existente pelo código")
    public ResponseEntity<CupomDTO> atualizar(@PathVariable String codigo, @Valid @RequestBody CupomDTO dto) {
        return ResponseEntity.ok(cupomService.atualizar(codigo, dto));
    }

    /**
     * Expira manualmente um cupom, tornando-o inválido.
     */
    @PatchMapping("/{codigo}/expirar")
    @Operation(summary = "Expirar cupom", description = "Marca o cupom como expirado, impedindo sua utilização")
    public ResponseEntity<Void> expirar(@PathVariable String codigo) {
        cupomService.expirar(codigo);
        return ResponseEntity.ok().build();
    }

    /**
     * Aplica um cupom a um pedido específico.
     */
    @PostMapping("/aplicar/{pedidoId}")
    @Operation(summary = "Aplicar cupom ao pedido", description = "Aplica um cupom válido ao pedido informado")
    public ResponseEntity<Void> aplicarCupom(@PathVariable Long pedidoId, @Valid @RequestBody CupomAplicacaoDTO dto) {
        cupomService.aplicarCupom(pedidoId, dto);
        return ResponseEntity.ok().build();
    }
}