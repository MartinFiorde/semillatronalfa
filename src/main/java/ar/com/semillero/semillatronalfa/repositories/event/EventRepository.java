package ar.com.semillero.semillatronalfa.repositories.event;

import ar.com.semillero.semillatronalfa.entities.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, String> {
    @Query(value = "select * from event where event.is_active = 1", nativeQuery = true)
    List<Event> getEventList();

    @Query(value = "select * from event where event.is_active = 1 and event.id = :id", nativeQuery = true)
    Optional<Event> findEventById(@Param("id") String id);
    
    @Query(value = "select * from event inner join event_details on event.id = event_details.event_id where event_details.instructor = 'Test Instructor asd'", nativeQuery = true)
    List<Event> getEventListByInstructor();

    @Query(value = "select * from event where event.is_active = 1  and event.title like %?1% " +
            "and event.offered_by_semillero = ?2 and event.status like %?3% and event.organized_by like %?4%" +
            " and event.type like %?5% and event.approach like %?6%", nativeQuery = true)
    List<Event> filterEvents(String title, Boolean offeredBySemillero, String status, String organizedBy, String type, String approach);
}
