package entity;


public class DescontoPercentual implements RegraCupom {
    private double percentual;
    public DescontoPercentual (double percentual) {
        
        if (percentual <= 0 || percentual >= 100) {
            throw new IllegalArgumentException("o percentual deve ser maior que 0, e menor que 100");
        }
this.percentual = percentual;
    }
    @Override
        public double aplicarDesconto (double valor) {
            return valorOriginal - (valorOriginal * (percentual/100));

        }
}
