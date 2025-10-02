package service;

import Entity.Cupom;
import java.util.List;
import java.util.UUID;



public class AtualizarCupom {
    private final BuscarCupomService buscarCupomService;

    public AtualizarCupomService(List<Cupom> cupons) {
        this.buscarCupomService = new BuscarCupomService(cupons);
    }

    public void atualizar(UUID codigo, double novoValor, boolean percentual) {
        Cupom cupom = buscarCupomService.executar(codigo);
        cupom.setValorDesconto(novoValor);
        cupom.setPercentual(percentual);
    }
}
 
