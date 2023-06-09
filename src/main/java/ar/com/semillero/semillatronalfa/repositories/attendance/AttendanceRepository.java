package ar.com.semillero.semillatronalfa.repositories.attendance;

import ar.com.semillero.semillatronalfa.entities.attendance.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, String> {

    @Query(value = "select attendance.* from attendance where event_id = :event_id", nativeQuery = true)
    List<Attendance> findEventAttendance(@Param("event_id") String eventId);
}
