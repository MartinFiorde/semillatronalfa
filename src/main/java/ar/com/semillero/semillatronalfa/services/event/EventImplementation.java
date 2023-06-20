package ar.com.semillero.semillatronalfa.services.event;

import ar.com.semillero.semillatronalfa.dtos.event.EventDto;
import ar.com.semillero.semillatronalfa.entities.event.Event;
import ar.com.semillero.semillatronalfa.queries.event.EventFilter;
import ar.com.semillero.semillatronalfa.repositories.event.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
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
        event.getDetails().setDuration((int) ChronoUnit.MINUTES.between(event.getDetails().getStartTime(), event.getDetails().getEndingTime()));
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

    @Override
    public Event findEvent(String id) {
        return eventRepository.findEventById(id).orElse(null);
    }

    @Override
    public void updateEvent(Event event, Event event2) {
        event2.setDate(event.getDate());
        event2.setTitle(event.getTitle());
        event2.setImage(event.getImage());
        event2.setApproach(event.getApproach());
        event2.setOfferedBySemillero(event.getOfferedBySemillero());
        event2.setOrganizedBy(event.getOrganizedBy());
        event2.setType(event.getType());
        event2.setStatus(event.getStatus());
        event2.getDetails().setDestination(event.getDetails().getDestination());
        event2.getDetails().setDuration((int) ChronoUnit.MINUTES.between(event.getDetails().getStartTime(), event.getDetails().getEndingTime()));
        event2.getDetails().setInstructor(event.getDetails().getInstructor());
        event2.getDetails().setDescription(event.getDetails().getDescription());
        event2.getDetails().setLocation(event.getDetails().getLocation());
        event2.getDetails().setOrigin(event.getDetails().getOrigin());
        event2.getDetails().setModality(event.getDetails().getModality());
        event2.getDetails().setStartTime(event.getDetails().getStartTime());
        event2.getDetails().setEndingTime(event.getDetails().getEndingTime());
        eventRepository.save(event2);
    }

    @Override
    public void addEventList(Event[] event) {
        for (Event e: event) {
            e.getDetails().setEvent(e);
            eventRepository.save(e);
        }
    }

    @Override
    public List<Event> filterEvent(EventFilter eventFilter) {
        return eventRepository.filterEvents(eventFilter.getTitle(), eventFilter.getOfferedBySemillero(),
                eventFilter.getStatus(), eventFilter.getOrganizedBy(), eventFilter.getType(), eventFilter.getApproach(),
                eventFilter.getDate(), eventFilter.getInstructor(), eventFilter.getDuration(), eventFilter.getModality(),
                eventFilter.getDestination());
    }
}