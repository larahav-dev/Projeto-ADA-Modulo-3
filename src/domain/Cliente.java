package domain;

import java.util.UUID;

public class Cliente {
    private final UUID id;
    private String nome;
    private String email;
    private final String documento;

    public Cliente(String nome, String email, String documento) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("Nome é obrigatório");
        if (documento == null || documento.isBlank()) throw new IllegalArgumentException("Documento é obrigatório");
        if (email == null || !email.contains("@")) throw new IllegalArgumentException("E-mail inválido");
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.email = email;
        this.documento = documento;
    }

    public void atualizar(String novoNome, String novoEmail) {
        if (novoNome == null || novoNome.isBlank()) throw new IllegalArgumentException("Nome é obrigatório");
        if (novoEmail == null || !novoEmail.contains("@")) throw new IllegalArgumentException("E-mail inválido");
        this.nome = novoNome;
        this.email = novoEmail;
    }

    public UUID getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getDocumento() { return documento; }

    @Override
    public String toString() {
        return "Cliente cadastrado com sucesso! \n"  +
                "ID: " + id + "\n" +
                " Nome=" + nome  +
                ", Email='" + email +
                ", Documento (CPF)=" + documento ;
    }
}
