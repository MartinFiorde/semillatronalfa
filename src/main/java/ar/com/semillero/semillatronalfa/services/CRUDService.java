package ar.com.semillero.semillatronalfa.services;

import ar.com.semillero.semillatronalfa.errors.ServiceRuntimeException;
import java.util.List;
import org.springframework.data.domain.Pageable;
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
    public List<D> getAll();

    @Transactional(readOnly = true)
    public List<D> getAllActives();

    @Transactional(readOnly = true)
    public List<D> searchAll(String q);

    @Transactional(readOnly = true)
    public List<D> searchAllActives(String q);

    public void validator(D d) throws ServiceRuntimeException;
}
