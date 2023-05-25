package ar.com.semillero.semillatronalfa.services.event;


import ar.com.semillero.semillatronalfa.entities.event.Event;

import java.util.List;

public interface EventService {

    List<Event> findEvents();

    List<Event> findEventList();

    void addEvent(Event event);

    Event findEventById(String id);

    void deleteEvent(String id);


}
