package ar.com.semillero.semillatronalfa.repositories;

import ar.com.semillero.semillatronalfa.models.SeedAttendance;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SeedAttendanceRepository extends JpaRepository<SeedAttendance, Object>{
    
    @Query(value = "select * from seed_attendance where seed_attendance.is_active = 1", nativeQuery = true)
    List<SeedAttendance> getSeedAttendanceList();
    
    @Query(value = "select * from seed_attendance where seed_attendance.seed_dni = :seed_dni and seed_attendance.event_id = :event_id", nativeQuery = true)
    public Optional<SeedAttendance> getSeedAttendanceBySeedDniAndEventId(@Param("seed_dni") Integer seedDni, @Param("event_id") String eventId);
}