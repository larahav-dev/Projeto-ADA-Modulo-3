package com.ada.ecommerce.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO para transferência de dados de cliente.
 */
public class ClienteDTO {

    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    private String nome;

    @NotBlank(message = "O documento é obrigatório")
    @Size(min = 11, max = 14, message = "O documento deve ter entre 11 e 14 caracteres")
    private String documento;

    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "E-mail inválido")
    private String email;

    public ClienteDTO() {}

    public ClienteDTO(Long id, String nome, String documento, String email) {
        this.id = id;
        this.nome = nome;
        this.documento = documento;
        this.email = email;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDocumento() { return documento; }
    public void setDocumento(String documento) { this.documento = documento; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}