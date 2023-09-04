package ar.com.semillero.semillatronalfa.repositories;

import ar.com.semillero.semillatronalfa.models.event.Event;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, String> {
    @Query(value = "select e.* from event e inner join event_details ed on ed.event_id = e.id where e.is_active = 1", nativeQuery = true)
    List<Event> getEventList();

    @Query(value = "select * from event where event.is_active = 1 and event.id = :id", nativeQuery = true)
    Optional<Event> findEventById(@Param("id") String id);

}
