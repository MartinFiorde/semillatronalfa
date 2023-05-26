package ar.com.semillero.semillatronalfa.services.ally;

import ar.com.semillero.semillatronalfa.entities.ally.Ally;
import ar.com.semillero.semillatronalfa.repositories.ally.AllyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllyImplementation implements AllyService {

    @Autowired
    AllyRepository allyRepository;

    @Override
    public List<Ally> findAllies() {
        return allyRepository.findAll();
    }

    @Override
    public void addAlly(Ally ally) {
        ally.getContactData().setAlly(ally);
        allyRepository.save(ally);
    }
}
