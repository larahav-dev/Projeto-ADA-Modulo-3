package service;

import entity.Cupom;
import java.util.UUID;

public class AtualizarCupomService {
    private final BuscarCupomService buscarCupomService;

    // Recebe a dependência já construída
    public AtualizarCupomService(BuscarCupomService buscarCupomService) {
        this.buscarCupomService = buscarCupomService;
    }

    public void atualizar(UUID codigo, double novoValor, boolean percentual) {
        Cupom cupom = buscarCupomService.executar(codigo);
        cupom.setValorDesconto(novoValor);
        cupom.setPercentual(percentual);
    }
}
