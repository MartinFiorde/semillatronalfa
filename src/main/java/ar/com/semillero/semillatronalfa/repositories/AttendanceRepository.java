package ar.com.semillero.semillatronalfa.repositories;

import ar.com.semillero.semillatronalfa.models.Attendance;
import ar.com.semillero.semillatronalfa.models.dtos.AttendanceDto;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, String> {

    @Query(value = "select attendance.* from attendance where event_id = :event_id", nativeQuery = true)
    List<Attendance> findEventAttendance(@Param("event_id") String eventId);

    @Query(value = "select a.full_name as full_name, a.dni as dni, CAST(a.timestamp as char) as timestamp from attendance a where a.event_id = :event_id", nativeQuery = true)
    List<Object[]> findEventAttendanceDto(@Param("event_id") String eventId);
}
