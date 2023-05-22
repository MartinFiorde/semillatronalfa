package ar.com.semillero.semillatronalfa.repositories;

import ar.com.semillero.semillatronalfa.entities.EventDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventDetailsRepository extends JpaRepository<EventDetails, String> {
}
