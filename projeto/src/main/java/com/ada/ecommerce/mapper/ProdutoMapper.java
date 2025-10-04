package com.ada.ecommerce.mapper;

import com.ada.ecommerce.dto.ProdutoDTO;
import com.ada.ecommerce.model.Produto;

/**
 * Mapper para conversão entre Produto e ProdutoDTO.
 */
public class ProdutoMapper {

    /**
     * Converte um DTO para a entidade Produto.
     *
     * @param dto ProdutoDTO
     * @return Produto (entidade)
     */
    public static Produto toEntity(ProdutoDTO dto) {
        if (dto == null) return null;

        Produto produto = new Produto();
        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setPrecoBase(dto.getPrecoBase());
        return produto;
    }

    /**
     * Converte uma entidade Produto para DTO.
     *
     * @param produto Produto (entidade)
     * @return ProdutoDTO
     */
    public static ProdutoDTO toDTO(Produto produto) {
        if (produto == null) return null;

        ProdutoDTO dto = new ProdutoDTO();
        dto.setId(produto.getId());
        dto.setNome(produto.getNome());
        dto.setDescricao(produto.getDescricao());
        dto.setPrecoBase(produto.getPrecoBase());
        return dto;
    }
}
