package entity;
import java.util.ArrayList;
import java.util.List;
public class RegraComposta implements RegraCupom {
    // (para combinar múltiplos descontos, por exemplo: fixo + percentual)
    private List<RegraCupom> regras = new ArrayList<>();

    public void adicionarRegra(RegraCupom regra) {
        regras.add(regra);
    }

    @Override
    public double aplicarDesconto(double valorOriginal) {
        double valor = valorOriginal;
        for (RegraCupom regra : regras) {
            valor = regra.aplicarDesconto(valor);
        }
        return valor;
    }
}