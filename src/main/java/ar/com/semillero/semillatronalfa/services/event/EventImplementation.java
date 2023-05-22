package ar.com.semillero.semillatronalfa.services.event;

import ar.com.semillero.semillatronalfa.entities.Event;
import ar.com.semillero.semillatronalfa.repositories.EventRepository;
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
}
