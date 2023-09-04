package ar.com.semillero.semillatronalfa.services.interfaces;


import ar.com.semillero.semillatronalfa.models.dtos.EventDto;
import ar.com.semillero.semillatronalfa.models.filters.EventFilter;
import ar.com.semillero.semillatronalfa.models.event.Event;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface EventService {

    List<EventDto> findEventList();

    void loadImage(MultipartFile image, Event event) throws IOException;

    List<Event> findEventListA();

    Event addEvent(Event event);

    EventDto findEventById(String id);

    void deleteEvent(String id);

    Event findEvent(String id);

    Event updateEvent(EventDto eventDto, Event event);

    void addEventList(List<EventDto> eventDtoList);

    List<EventDto> filterEvent(EventFilter eventFilter);
}
