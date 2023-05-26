package ar.com.semillero.semillatronalfa.services.commission;

import ar.com.semillero.semillatronalfa.entities.Commission;

import java.util.List;

public interface CommissionService {

    List<Commission> findAllCommissions();

    void addCommission(Commission commission);

    Commission findCommissionById(String id);
    
    Commission findCommissionByName(String name);
    
}

