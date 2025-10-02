package domain;

import java.math.BigDecimal;

public class Produto {

    private Integer id; // gerado pelo repositório
    private String nome;
    private String descricao;
    private BigDecimal preco;

    public Produto(String nome, String descricao, BigDecimal preco) {
        validarCampos(nome, descricao, preco);
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }

    public void definirId(Integer id) {
        if (this.id != null) return; // evita sobrescrever
        this.id = id;
    }

    public void atualizar(String nome, String descricao, BigDecimal preco) {
        validarCampos(nome, descricao, preco);
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }

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

    public Integer getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public String getDescricao() {
        return descricao;
    }
    public BigDecimal getPreco() {
        return preco;
    }

    @Override
    public String toString() {
        return "Produto cadastrado com sucesso! \n"  +
                "ID:"+ id + "\n"+
                ", Nome='" + nome + '\'' +
                ", Descrição='" + descricao + '\'' +
                ", Preço= R$:'" + preco + '\'' +
                '}';
    }
}
