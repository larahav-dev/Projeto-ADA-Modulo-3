package service;

import entity.Cupom;
import java.util.UUID;

public interface CupomUseCase {
    Cupom executar(UUID codigo);
}
 