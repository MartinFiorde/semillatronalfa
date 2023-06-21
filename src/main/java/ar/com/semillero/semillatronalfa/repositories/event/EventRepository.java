package ar.com.semillero.semillatronalfa.repositories.event;

import ar.com.semillero.semillatronalfa.entities.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, String> {
    @Query(value = "select * from event where event.is_active = 1", nativeQuery = true)
    List<Event> getEventList();

    @Query(value = "select * from event where event.is_active = 1 and event.id = :id", nativeQuery = true)
    Optional<Event> findEventById(@Param("id") String id);

    /*@Query(value = "select event.* from event " +
            " inner join event_details on event.id = event_details.event_id " +
            " and event.date = ?7 and event_details.instructor like %?8% " +
            " and event_details.duration = isnull(?9, event_details.duration) and event_details.modality like %?10% " +
            " and event_details.destination like %?11%"+
            " where event.is_active = 1  and event.title like %?1% " +
            " and event.offered_by_semillero = (case when isnull(?2) then 1 else ?2 end) " +
            " or  event.offered_by_semillero = (case when isnull(?2) then 0 else ?2 end) " +
            " and event.status like %?3% and event.organized_by like %?4%" +
            " and event.type like %?5% and event.approach like %?6% "  , nativeQuery = true)
    List<Event> filterEvents(String title, Boolean offeredBySemillero, String status, String organizedBy, String type, String approach,
                             LocalDate date, String instructor, int duration, String modality, String destination);*/

    // TODO: Mejorar filtrado por duración y por fecha
}
