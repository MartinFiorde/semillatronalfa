package ar.com.semillero.semillatronalfa.services.interfaces;

import ar.com.semillero.semillatronalfa.models.dtos.UserDto;
import ar.com.semillero.semillatronalfa.configs.errors.ServiceRuntimeException;
import ar.com.semillero.semillatronalfa.services.interfaces.CRUDService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface UserService extends CRUDService<UserDto>{

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public UserDto register(UserDto d, String passwordConfirm) throws ServiceRuntimeException, Exception;
    
}
