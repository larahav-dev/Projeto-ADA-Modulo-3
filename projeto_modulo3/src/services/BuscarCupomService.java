package service;

import entity.Cupom;
import java.util.List;
import java.util.UUID;

public class BuscarCupomService implements CupomUseCase {
    private final List<Cupom> cupons;

    public BuscarCupomService(List<Cupom> cupons) {
        this.cupons = cupons;
    }

    @Override
    public Cupom executar(UUID codigo) {
        return cupons.stream()
                .filter(c -> c.getCodigo().equals(codigo))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Cupom não encontrado."));
    }
}