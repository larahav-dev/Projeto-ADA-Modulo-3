package com.ada.ecommerce.service;

import com.ada.ecommerce.dto.ProdutoDTO;
import com.ada.ecommerce.mapper.ProdutoMapper;
import com.ada.ecommerce.model.Produto;
import com.ada.ecommerce.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Serviço responsável pelas operações de negócio relacionadas a produtos.
 */
@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    /**
     * Cadastra um novo produto.
     *
     * @param dto dados do produto
     * @return produto criado com ID preenchido
     */
    public ProdutoDTO cadastrar(ProdutoDTO dto) {
        Produto produto = ProdutoMapper.toEntity(dto);
        repository.save(produto);
        return ProdutoMapper.toDTO(produto);
    }

    /**
     * Lista todos os produtos cadastrados.
     *
     * @return lista de produtos
     */
    public List<ProdutoDTO> listar() {
        return repository.findAll().stream()
                .map(ProdutoMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Atualiza os dados de um produto existente.
     *
     * @param id  identificador do produto
     * @param dto dados atualizados
     * @return produto atualizado
     */
    public ProdutoDTO atualizar(Long id, ProdutoDTO dto) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado com ID: " + id));

        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setPrecoBase(dto.getPrecoBase());
        repository.save(produto);

        return ProdutoMapper.toDTO(produto);
    }

    /**
     * Busca produtos pelo nome (parcial, ignorando maiúsculas/minúsculas).
     *
     * @param nome termo de busca
     * @return lista de produtos correspondentes
     */
    public List<ProdutoDTO> buscarPorNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome).stream()
                .map(ProdutoMapper::toDTO)
                .collect(Collectors.toList());
    }
}