package service;
import entity.Cupom;
    import java.util.ArrayList;
    import java.util.List;
import java.util.UUID;

public class  CupomService {
        private List<Cupom> cupons = new ArrayList<>();
    //cria o método de criar cupons 
        public void  criaCupom (Cupom cupom) {
            cupons.add(cupom);
        }
        public List<Cupom> listarCupons () {
        return new ArrayList<>(cupons);
        }

    
}
