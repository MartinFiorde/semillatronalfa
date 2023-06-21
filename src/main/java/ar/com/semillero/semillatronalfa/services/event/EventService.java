package ar.com.semillero.semillatronalfa.services.event;


import ar.com.semillero.semillatronalfa.dtos.event.EventDto;
import ar.com.semillero.semillatronalfa.entities.event.Event;
import ar.com.semillero.semillatronalfa.queries.event.EventFilter;


import java.util.List;

public interface EventService {

    List<EventDto> findEventList();

    void addEvent(Event event);

    EventDto findEventById(String id);

    void deleteEvent(String id);

    Event findEvent(String id);

    void updateEvent(Event event, Event event2);

    void addEventList(Event[] event);

    List<Event> filterEvent(EventFilter eventFilter);

    List<Event> searchEvent();
}
