package com.ada.ecommerce.service;

import com.ada.ecommerce.dto.ClienteDTO;
import com.ada.ecommerce.model.Cliente;
import com.ada.ecommerce.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Serviço responsável pelas operações de negócio relacionadas a clientes.
 */
@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    /**
     * Cadastra um novo cliente.
     *
     * @param dto dados do cliente
     * @return cliente criado com ID preenchido
     */
    public ClienteDTO cadastrar(ClienteDTO dto) {
        Cliente cliente = new Cliente(dto.getNome(), dto.getDocumento(), dto.getEmail());
        repository.save(cliente);
        dto.setId(cliente.getId());
        return dto;
    }

    /**
     * Lista todos os clientes cadastrados.
     *
     * @return lista de clientes
     */
    public List<ClienteDTO> listar() {
        return repository.findAll().stream()
                .map(c -> new ClienteDTO(c.getId(), c.getNome(), c.getDocumento(), c.getEmail()))
                .collect(Collectors.toList());
    }

    /**
     * Atualiza os dados de um cliente existente.
     *
     * @param id  identificador do cliente
     * @param dto dados atualizados
     * @return cliente atualizado
     */
    public ClienteDTO atualizar(Long id, ClienteDTO dto) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado com ID: " + id));

        cliente.setNome(dto.getNome());
        cliente.setEmail(dto.getEmail());
        repository.save(cliente);

        dto.setId(cliente.getId());
        dto.setDocumento(cliente.getDocumento()); // preserva o documento original
        return dto;
    }

    /**
     * Busca um cliente pelo CPF ou CNPJ.
     *
     * @param documento CPF ou CNPJ
     * @return cliente correspondente
     */
    public ClienteDTO buscarPorDocumento(String documento) {
        Cliente cliente = repository.findByDocumento(documento)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado com documento: " + documento));

        return new ClienteDTO(cliente.getId(), cliente.getNome(), cliente.getDocumento(), cliente.getEmail());
    }
}