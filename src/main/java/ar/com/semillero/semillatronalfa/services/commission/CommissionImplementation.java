package ar.com.semillero.semillatronalfa.services.commission;

import ar.com.semillero.semillatronalfa.entities.Commission;
import ar.com.semillero.semillatronalfa.repositories.commission.CommissionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommissionImplementation implements CommissionService {

    private CommissionRepository commissionRepository;

    // seed
    @Autowired
    public CommissionImplementation(CommissionRepository commissionRepository) {
        this.commissionRepository = commissionRepository;
    }

    @Override
    public List<Commission> getAll() {
        return commissionRepository.findAll();
    }

    @Override
    public List<Commission> getAllActives() {
        return commissionRepository.findAll();
    }

    @Override
    public void save(Commission incomingCommission) {
        System.out.println("incomingCom" + incomingCommission);
        Commission outcomingCommission = new Commission();
        outcomingCommission.setName(validateName(incomingCommission.getName()));
        outcomingCommission.setIsActive(incomingCommission.getIsActive());
        commissionRepository.save(outcomingCommission);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void saveBatch(List<Commission> commissions) {
        for (Commission commission : commissions) {
            commissionRepository.save(commission);
        }
    }

    @Override
    public Commission findCommissionById(String id) {
        return commissionRepository.findById(id).orElse(null);
    }

    @Override
    public Commission findCommissionByName(String name) {
        return commissionRepository.findByName(name).orElse(null);
    }

    public String validateName(String name) {
        // AGREGAR VALIDACIÓN DEL DATO
        return name;
    }

    @Override
    public Commission deactivate(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Commission activate(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
