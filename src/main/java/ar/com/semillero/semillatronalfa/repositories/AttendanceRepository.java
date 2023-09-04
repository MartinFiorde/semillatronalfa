package ar.com.semillero.semillatronalfa.repositories;

import ar.com.semillero.semillatronalfa.models.Attendance;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, String> {

    @Query(value = "select attendance.* from attendance where event_id = :event_id", nativeQuery = true)
    List<Attendance> findEventAttendance(@Param("event_id") String eventId);
}
