package ar.com.semillero.semillatronalfa.services.event;


import ar.com.semillero.semillatronalfa.dtos.event.EventDto;
import ar.com.semillero.semillatronalfa.entities.event.Event;

import java.util.List;

public interface EventService {

    List<EventDto> findEventList();

    void addEvent(Event event);

    EventDto findEventById(String id);

    void deleteEvent(String id);


}
