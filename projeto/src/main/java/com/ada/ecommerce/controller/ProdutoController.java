package com.ada.ecommerce.controller;

import com.ada.ecommerce.dto.ProdutoDTO;
import com.ada.ecommerce.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller responsável pelos endpoints de gerenciamento de produtos.
 */
@Order(3)
@RestController
@RequestMapping("/api/produtos")
@Tag(name = "Produtos", description = "Endpoints para gerenciamento de produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    /**
     * Cadastra um novo produto.
     *
     * @param dto dados do produto
     * @return produto criado
     */
    @PostMapping
    @Operation(summary = "Cadastrar produto", description = "Cria um novo produto no sistema")
    public ResponseEntity<ProdutoDTO> cadastrar(@Valid @RequestBody ProdutoDTO dto) {
        ProdutoDTO produtoCriado = produtoService.cadastrar(dto);
        return ResponseEntity.status(201).body(produtoCriado);
    }

    /**
     * Lista todos os produtos cadastrados.
     *
     * @return lista de produtos
     */
    @GetMapping
    @Operation(summary = "Listar produtos", description = "Retorna todos os produtos cadastrados")
    public ResponseEntity<List<ProdutoDTO>> listar() {
        return ResponseEntity.ok(produtoService.listar());
    }

    /**
     * Atualiza os dados de um produto existente.
     *
     * @param id identificador do produto
     * @param dto dados atualizados
     * @return produto atualizado
     */
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar produto", description = "Atualiza os dados de um produto existente")
    public ResponseEntity<ProdutoDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ProdutoDTO dto) {
        ProdutoDTO atualizado = produtoService.atualizar(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    /**
     * Busca produtos cujo nome contenha o termo informado.
     *
     * @param nome termo de busca
     * @return lista de produtos correspondentes
     */
    @GetMapping("/busca")
    @Operation(summary = "Buscar produtos por nome", description = "Retorna produtos cujo nome contenha o termo informado")
    public ResponseEntity<List<ProdutoDTO>> buscarPorNome(@RequestParam String nome) {
        List<ProdutoDTO> produtos = produtoService.buscarPorNome(nome);
        return produtos.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(produtos);
    }
}