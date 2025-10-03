package com.ada.ecommerce.controller;

import com.ada.ecommerce.dto.ClienteDTO;
import com.ada.ecommerce.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@Tag(name = "Clientes", description = "Endpoints para gerenciamento de clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    @Operation(summary = "Cadastrar cliente", description = "Cria um novo cliente no sistema")
    public ResponseEntity<ClienteDTO> cadastrar(@Valid @RequestBody ClienteDTO dto) {
        ClienteDTO clienteCriado = clienteService.cadastrar(dto);
        return ResponseEntity.status(201).body(clienteCriado);
    }

    @GetMapping
    @Operation(summary = "Listar clientes", description = "Retorna todos os clientes cadastrados")
    public ResponseEntity<List<ClienteDTO>> listar() {
        return ResponseEntity.ok(clienteService.listar());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar cliente", description = "Atualiza os dados de um cliente existente")
    public ResponseEntity<ClienteDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ClienteDTO dto) {
        return ResponseEntity.ok(clienteService.atualizar(id, dto));
    }

    @GetMapping("/documento/{documento}")
    @Operation(summary = "Buscar cliente por documento", description = "Retorna os dados de um cliente pelo CPF ou CNPJ")
    public ResponseEntity<ClienteDTO> buscarPorDocumento(@PathVariable String documento) {
        ClienteDTO cliente = clienteService.buscarPorDocumento(documento);
        return ResponseEntity.ok(cliente);
    }
}
