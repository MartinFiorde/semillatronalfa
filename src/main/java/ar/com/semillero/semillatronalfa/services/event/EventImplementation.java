package ar.com.semillero.semillatronalfa.services.event;

import ar.com.semillero.semillatronalfa.dtos.event.EventDto;
import ar.com.semillero.semillatronalfa.entities.event.Event;
import ar.com.semillero.semillatronalfa.repositories.event.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class EventImplementation implements EventService {

    @Autowired
    EventRepository eventRepository;

    @Override
    public List<EventDto> findEventList() {
        return eventRepository.getEventList().stream().map(EventDto::new).collect(Collectors.toList());
    }

    @Override
    public void addEvent(Event event) {
        event.getDetails().setEvent(event);
        eventRepository.save(event);
    }

    @Override
    public EventDto findEventById(String id) {
        return new EventDto(Objects.requireNonNull(eventRepository.findEventById(id)).orElse(null));
    }

    @Override
    public void deleteEvent(String id) {
        Event event = eventRepository.findById(id).orElse(null);
        if (event == null) throw new AssertionError();
        event.setActive(false);
        eventRepository.save(event);
    }
}
