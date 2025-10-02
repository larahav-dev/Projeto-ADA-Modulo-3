package domain.validation;

import domain.Produto;

import java.math.BigDecimal;

public class ProdutoValidator {

    public void validarNovo(Produto produto) {
        validarCampos(produto.getNome(), produto.getDescricao(), produto.getPreco());
    }

    public void validarAtualizacao(String nome, String descricao, BigDecimal preco) {
        validarCampos(nome, descricao, preco);
    }

    /**
     * Valida os campos obrigatórios de um produto.
     */
    private void validarCampos(String nome, String descricao, BigDecimal preco) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome do produto é obrigatório.");
        }
        if (descricao == null || descricao.isBlank()) {
            throw new IllegalArgumentException("A descrição do produto é obrigatória.");
        }
        if (preco == null || preco.signum() <= 0) {
            throw new IllegalArgumentException("O preço do produto deve ser maior que zero.");
        }
    }
}
