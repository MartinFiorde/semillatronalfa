package ar.com.semillero.semillatronalfa.services.event;

import ar.com.semillero.semillatronalfa.entities.event.Event;
import ar.com.semillero.semillatronalfa.repositories.event.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventImplementation implements EventService {

    @Autowired
    EventRepository eventRepository;

    @Override
    public List<Event> findEvents() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> findEventList() {
        return eventRepository.getEventList();
    }

    @Override
    public void addEvent(Event event) {
        event.getDetails().setEvent(event);
        eventRepository.save(event);
    }

    @Override
    public Event findEventById(String id) {
        return eventRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteEvent(String id) {
        Event event = eventRepository.findById(id).orElse(null);
        event.setActive(false);
        eventRepository.save(event);
    }
}
