package ar.com.semillero.semillatronalfa.repositories.event;

import ar.com.semillero.semillatronalfa.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, String> {
}
