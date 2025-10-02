package domain.validation;

import domain.Cliente;
import ports.ClienteRepository;

public class ClienteValidator {

    private final ClienteRepository clienteRepository;

    public ClienteValidator(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    /**
     * Valida um novo cliente antes de ser salvo.
     */
    public void validarNovo(Cliente cliente) {
        if (!isCpfValido(cliente.getDocumento())) {
            throw new IllegalArgumentException("CPF inválido");
        }
        boolean docJaExiste = clienteRepository.existePorDocumento(cliente.getDocumento());
        if (docJaExiste) {
            throw new IllegalArgumentException("Documento já cadastrado");
        }
    }

    /**
     * Valida dados de atualização de cliente.
     */
    public void validarAtualizacao(String nome, String email) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome é obrigatório");
        }
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("E-mail inválido");
        }
    }

    /**
     * Valida CPF considerando formato e dígitos verificadores.
     */
    private boolean isCpfValido(String cpf) {
        if (cpf == null) return false;

        // Remove caracteres não numéricos
        cpf = cpf.replaceAll("\\D", "");

        // CPF deve ter 11 dígitos
        if (cpf.length() != 11) return false;

        // Elimina CPFs com todos os dígitos iguais
        if (cpf.matches("(\\d)\\1{10}")) return false;

        try {
            int soma = 0;
            for (int i = 0; i < 9; i++) {
                soma += (cpf.charAt(i) - '0') * (10 - i);
            }
            int resto = 11 - (soma % 11);
            char digito1 = (resto == 10 || resto == 11) ? '0' : (char) (resto + '0');

            soma = 0;
            for (int i = 0; i < 10; i++) {
                soma += (cpf.charAt(i) - '0') * (11 - i);
            }
            resto = 11 - (soma % 11);
            char digito2 = (resto == 10 || resto == 11) ? '0' : (char) (resto + '0');

            return digito1 == cpf.charAt(9) && digito2 == cpf.charAt(10);
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
