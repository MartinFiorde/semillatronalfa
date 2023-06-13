package ar.com.semillero.semillatronalfa.repositories.event;

import ar.com.semillero.semillatronalfa.entities.event.EventDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventDetailsRepository extends JpaRepository<EventDetails, String> {
}
