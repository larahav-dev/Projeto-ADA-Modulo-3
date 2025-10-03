    package service;

    import entity.Cupom;
            import java.util.UUID;
public class MarcarCupomUtilizadoService {
        private final BuscarCupomService buscarCupomService;

            public MarcarCupomUtilizadoService(BuscarCupomService buscarCupomService) {
                this.buscarCupomService = buscarCupomService;
}
    public void marcarComoUtilizado(UUID codigo) {
        Cupom cupom = buscarCupomService.executar(codigo);

        if (cupom.isValido()) {
            cupom.marcarComoUtilizado();
        } else {
            throw new IllegalArgumentException("Cupom inválido, expirado ou já utilizado.");
        }
}
}