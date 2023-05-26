package ar.com.semillero.semillatronalfa.services.commission;

import ar.com.semillero.semillatronalfa.entities.Commission;
import ar.com.semillero.semillatronalfa.repositories.Commission.CommissionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommissionImplementation implements CommissionService {

    private CommissionRepository commissionRepository;

    @Autowired
    public CommissionImplementation(CommissionRepository commissionRepository) {
        this.commissionRepository = commissionRepository;
    }

    @Override
    public List<Commission> findAllCommissions() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addCommission(Commission incomingCommission) {
        Commission outcomingCommission = new Commission();
        outcomingCommission.setName(validateName(incomingCommission.getName()));
        commissionRepository.save(outcomingCommission);
    }

    @Override
    public Commission findCommissionById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Commission findCommissionByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String validateName(String name) {
        // AGREGAR VALIDACIÓN DEL DATO
        return name;
    }

}
