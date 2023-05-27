package ar.com.semillero.semillatronalfa.repositories.event;

import ar.com.semillero.semillatronalfa.entities.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, String> {
    @Query(value = "select * from event where event.is_active = 1", nativeQuery = true)
    List<Event> getEventList();
    
    @Query(value = "select * from event inner join event_details on event.id = event_details.event_id where event_details.instructor = 'Test Instructor asd'", nativeQuery = true)
    List<Event> getEventListByInstructor();
}
