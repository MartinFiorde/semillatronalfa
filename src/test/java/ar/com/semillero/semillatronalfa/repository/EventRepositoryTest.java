package ar.com.semillero.semillatronalfa.repository;

import ar.com.semillero.semillatronalfa.models.event.Event;
import ar.com.semillero.semillatronalfa.models.event.EventDetails;
import ar.com.semillero.semillatronalfa.repositories.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EventRepositoryTest {

    @Autowired
    private EventRepository eventRepository;

    private Event event;
    private EventDetails eventDetails;

    private Event event2;
    private EventDetails eventDetails2;

    @BeforeEach
    void setup() {
        event = new Event();
        eventDetails = new EventDetails();

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

    // Se comprueba que se guarde el evento y se verifica si coincide el título y la modalidad
    @Test
    @DisplayName("Save event test")
    void saveEventTest() {
        Event newEvent = eventRepository.save(event);

        assertNotNull(newEvent);
        assertEquals("TESTING", newEvent.getTitle());
        assertEquals("MIXTO", newEvent.getDetails().getModality());
    }

    // Se espera que se retornen los eventos en una lista
    @Test
    @DisplayName("Fetch events list test")
    void getEventListTest() {
        Event newEvent = eventRepository.save(event);
        Event newEvent2 = eventRepository.save(event2);

        List<Event> eventList = eventRepository.findAll();

        assertNotNull(eventList);
        assertEquals(4, eventList.size());
    }

    @Test
    @DisplayName("Fetch event by id test")
    void getEventByIdTest() {
        Event newEvent = eventRepository.save(event);

        Event fetchedEvent = eventRepository.findEventById(newEvent.getId()).get();

        assertNotNull(fetchedEvent);
        assertEquals("TESTING", fetchedEvent.getTitle());
    }

    @Test
    @DisplayName("Update event test")
    void updateEventTest() {
        Event newEvent = eventRepository.save(event);

        Event eventToUpdate = eventRepository.findEventById(newEvent.getId()).get();
        eventToUpdate.setTitle("UPDATED TITLE FROM TEST");
        eventToUpdate.setOrganizedBy("UDEMY");

        assertNotNull(eventToUpdate);
        assertEquals("UPDATED TITLE FROM TEST", eventToUpdate.getTitle());
        assertEquals("UDEMY", eventToUpdate.getOrganizedBy());
    }

}
