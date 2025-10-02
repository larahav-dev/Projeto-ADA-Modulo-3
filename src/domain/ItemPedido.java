package domain;

import java.math.BigDecimal;

public class ItemPedido {
    private final Produto produto;
    private int quantidade;
    private BigDecimal precoUnitario; // preço negociado no pedido

    public ItemPedido(Produto produto, int quantidade, BigDecimal precoUnitario) {
        if (produto == null) throw new IllegalArgumentException("Produto é obrigatório.");
        if (quantidade <= 0) throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
        if (precoUnitario == null || precoUnitario.signum() <= 0) {
            throw new IllegalArgumentException("Preço unitário deve ser maior que zero.");
        }
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void alterarQuantidade(int novaQuantidade) {
        if (novaQuantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
        }
        this.quantidade = novaQuantidade;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void alterarPrecoUnitario(BigDecimal novoPreco) {
        if (novoPreco == null || novoPreco.signum() <= 0) {
            throw new IllegalArgumentException("Preço unitário deve ser maior que zero.");
        }
        this.precoUnitario = novoPreco;
    }

    public BigDecimal subtotal() {
        return precoUnitario.multiply(BigDecimal.valueOf(quantidade));
    }

    public Produto getProduto() {
        return produto;
    }
}
