package service;

import entity.Cupom;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;



public class ExpirarCupom {
    private final BuscarCupomService buscarCupomService;

    public ExpirarCupomService(List<Cupom> cupons) {
        this.buscarCupomService = new BuscarCupomService(cupons);
    }

    public void expirar(UUID codigo) {
        Cupom cupom = buscarCupomService.executar(codigo);
        cupom.setDataValidade(LocalDate.now().minusDays(1));
    }
}
