package ar.com.semillero.semillatronalfa.services;

import ar.com.semillero.semillatronalfa.models.converters.EventConverter;
import ar.com.semillero.semillatronalfa.models.dtos.EventDto;
import ar.com.semillero.semillatronalfa.models.event.Event;
import ar.com.semillero.semillatronalfa.models.event.EventDetails;
import ar.com.semillero.semillatronalfa.repositories.EventRepository;
import ar.com.semillero.semillatronalfa.services.EventServiceImpl;
import ar.com.semillero.semillatronalfa.services.interfaces.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventServiceImpl eventService;

    @Autowired
    private EventConverter eventConverter;

    private Event event;
    private EventDetails eventDetails;

    private Event event2;
    private EventDetails eventDetails2;

    @BeforeEach
    void setup() {
        event = new Event();
        eventDetails = new EventDetails();
        event.setId("abc");
        event.setTitle("TESTING");
        event.setApproach("APPROACH");
        event.setOrganizedBy("Semillero");
        event.setType("Taller");
        event.setOfferedBySemillero(true);
        eventDetails.setOrigin("INTERNO");
        eventDetails.setDestination("GENERAL");
        eventDetails.setModality("MIXTO");
        eventDetails.setInstructor("JPD");
        event.setDetails(eventDetails);
        eventDetails.setEvent(event);

        event2 = new Event();
        eventDetails2 = new EventDetails();

        event2.setTitle("TESTING");
        event2.setApproach("APPROACH");
        event2.setOrganizedBy("Semillero");
        event2.setType("Taller");
        event2.setOfferedBySemillero(true);
        eventDetails2.setOrigin("INTERNO");
        eventDetails2.setDestination("GENERAL");
        eventDetails2.setModality("MIXTO");
        eventDetails2.setInstructor("JPD");
        event2.setDetails(eventDetails2);
        eventDetails2.setEvent(event2);
    }

    @Test
    void save() {

        when(eventRepository.save(any(Event.class))).thenReturn(event);


        Event createdEvent = eventService.addEvent(event);

        assertNotNull(createdEvent);
        assertEquals("TESTING", event.getTitle());
        assertEquals("APPROACH", event.getApproach());
    }

    @Test
    @DisplayName("Fetch events list test")
    void getEventListTest() {
        List<Event> eventListStart = eventRepository.findAll();
        Event newEvent = eventRepository.save(event);
        Event newEvent2 = eventRepository.save(event2);

        List<Event> eventListEnd = eventRepository.findAll();

        assertNotNull(eventListEnd);
        assertEquals(2, eventListEnd.size() - eventListStart.size());
    }

    @Test
    void findEventByIdTest() {

        when(eventRepository.findEventById(anyString())).thenReturn(Optional.of(event));

        Event foundEvent = eventService.findEvent(event.getId());

        assertNotNull(foundEvent);
        assertEquals("abc", foundEvent.getId());
        assertEquals("TESTING", foundEvent.getTitle());
    }

    @Test
    void updateEventTest() {
        when(eventRepository.findEventById(anyString())).thenReturn(Optional.of(event));
        when(eventRepository.save(any(Event.class))).thenReturn(event);

        Event foundEvent = eventRepository.findEventById(event.getId()).get();
        foundEvent.setTitle("Updated title");
        foundEvent.getDetails().setLocation("Concordia");
        Event updatedEvent = eventService.updateEvent(eventConverter.entityToDto(foundEvent), event);

        assertNotNull(updatedEvent);
        assertEquals("abc", updatedEvent.getId());
        assertEquals("Updated title", updatedEvent.getTitle());
        assertEquals("Concordia", updatedEvent.getDetails().getLocation());
    }
}
