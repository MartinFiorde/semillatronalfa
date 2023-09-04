package ar.com.semillero.semillatronalfa.services.interfaces;

import ar.com.semillero.semillatronalfa.configs.errors.ServiceRuntimeException;
import java.util.List;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface CRUDService<D> {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public D save(D d) throws ServiceRuntimeException, Exception;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public D edit(D d) throws ServiceRuntimeException, Exception;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public D deactivate(String id);

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public D activate(String id);

    @Transactional(readOnly = true)
    public D getOne(String id);

    @Transactional(readOnly = true)
    public List<D> getAllActives();
}
