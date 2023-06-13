package ar.com.semillero.semillatronalfa.services.commission;

import ar.com.semillero.semillatronalfa.entities.Commission;
import ar.com.semillero.semillatronalfa.services.CRUDService;

import java.util.List;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface CommissionService /*extends CRUDService<Commission>*/{

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    void save(Commission commission);
    
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    void saveBatch(List<Commission> commissions);

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public Commission deactivate(String id);

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public Commission activate(String id);
    
    @Transactional(readOnly = true)
    List<Commission> getAll();
    
    @Transactional(readOnly = true)
    List<Commission> getAllActives();

    
    @Transactional(readOnly = true)
    Commission findCommissionById(String id);
    
    Commission findCommissionByName(String name);
    
}

