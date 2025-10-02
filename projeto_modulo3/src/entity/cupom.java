package entity;

import java.time.LocalDate;
import java.util.UUID;

public class Cupom {
    private final  UUID codigo;
    private double valorDesconto;
    private boolean percentual;
    private LocalDate dataValidade;
    private boolean utilizado;
    // criando o construtor, o cupom vai  nascer como não usado

    public cupom(double valorDesconto, boolean percentual, LocalDate dataValidade, boolean utilizado) {
            if (valorDesconto <= 0) {
                throw new IllegalArgumentException("o valor do desconto deverá ser maior que 0");
            }
        if (dataValidade.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("A data de validade não pode ser no passado.");
        }

        this.codigo = UUID.randomUUID();
        this.valorDesconto = valorDesconto;
        this.percentual = percentual;
        this.dataValidade = dataValidade;
        this.utilizado = utilizado;
    }

    public boolean isValido() {
        return !utilizado && LocalDate.now().isBefore(dataValidade);
    }

    public UUID getCodigo() {
        return codigo;
    }

    public double  getValorDesconto() {
        return valorDesconto;
    }

    public isPercentual() {
                return percentual;

        }

}