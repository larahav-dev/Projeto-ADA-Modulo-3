package com.ada.ecommerce.controller;

import com.ada.ecommerce.dto.ProdutoDTO;
import com.ada.ecommerce.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
@Tag(name = "Produtos", description = "Endpoints para gerenciamento de produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    @Operation(summary = "Cadastrar produto", description = "Cria um novo produto no sistema")
    public ResponseEntity<ProdutoDTO> cadastrar(@Valid @RequestBody ProdutoDTO dto) {
        ProdutoDTO produtoCriado = produtoService.cadastrar(dto);
        return ResponseEntity.status(201).body(produtoCriado);
    }

    @GetMapping
    @Operation(summary = "Listar produtos", description = "Retorna todos os produtos cadastrados")
    public ResponseEntity<List<ProdutoDTO>> listar() {
        return ResponseEntity.ok(produtoService.listar());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar produto", description = "Atualiza os dados de um produto existente")
    public ResponseEntity<ProdutoDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ProdutoDTO dto) {
        return ResponseEntity.ok(produtoService.atualizar(id, dto));
    }

    @GetMapping("/busca")
    @Operation(summary = "Buscar produtos por nome", description = "Retorna produtos cujo nome contenha o termo informado")
    public ResponseEntity<List<ProdutoDTO>> buscarPorNome(@RequestParam String nome) {
        List<ProdutoDTO> produtos = produtoService.buscarPorNome(nome);
        if (produtos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(produtos);
    }
}