package ar.com.semillero.semillatronalfa.services.ally;

import ar.com.semillero.semillatronalfa.dtos.allies.AllyDto;
import ar.com.semillero.semillatronalfa.entities.ally.Ally;

import java.util.List;

public interface AllyService {

    List<Ally> findAllies();

    void addAlly(Ally ally);

    AllyDto findAllyById(String id);
}
