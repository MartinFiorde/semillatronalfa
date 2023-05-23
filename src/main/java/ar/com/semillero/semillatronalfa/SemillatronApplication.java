package ar.com.semillero.semillatronalfa;

import ar.com.semillero.semillatronalfa.entities.Event;
import ar.com.semillero.semillatronalfa.entities.EventDetails;
import ar.com.semillero.semillatronalfa.enums.events.Destinations;
import ar.com.semillero.semillatronalfa.repositories.EventDetailsRepository;
import ar.com.semillero.semillatronalfa.repositories.EventRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class}) // (exclude = {UserDetailsServiceAutoConfiguration.class}) >> quita el mensaje de auto generated password
public class SemillatronApplication {

    public static void main(String[] args) {
        SpringApplication.run(SemillatronApplication.class, args);


        }
    @Bean
    public CommandLineRunner initData(EventRepository eventRepository, EventDetailsRepository eventDetailsRepository){
        return (args) -> {

            Event event = new Event(new Date(), "Curso Java", true, "Status", "Semillero", "Approach", "Curso");
            EventDetails details = new EventDetails("Test Instructor", "Semillero", Destinations.SEMILLERO.toString(), 120, "online", event);

            event.setDetails(details);
            eventRepository.save(event);

    };
    }



}
