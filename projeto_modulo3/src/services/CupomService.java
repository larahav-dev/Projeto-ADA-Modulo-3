package service;
import entity.Cupom;
    import java.util.ArrayList;
    import java.util.List;
import java.util.UUID;

public class  CupomService {
        private final  List<Cupom> cupons = new ArrayList<>();
    //cria o método de criar cupons 
        public void  criaCupom (Cupom cupom) {
                if (cupom == null) {
                    throw new IllegalArgumentException("o cupom não pode ser nulo");

                } 
                    
                
            cupons.add(cupom);
        }
        public List<Cupom> listarCupons () {
            return cupons.stream()
                .filter(Cupom::isValido)
                    .toList();
        }
//lista todos os cupons, tantos os válidos e tanto como os expirados
        public List<Cupom> listarTodos() {
            return new ArrayList<>(cupons);
            
        }
}
