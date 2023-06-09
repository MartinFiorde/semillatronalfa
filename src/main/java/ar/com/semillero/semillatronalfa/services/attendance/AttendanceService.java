package ar.com.semillero.semillatronalfa.services.attendance;

import ar.com.semillero.semillatronalfa.entities.attendance.Attendance;
import ar.com.semillero.semillatronalfa.entities.event.Event;

import java.util.List;

public interface AttendanceService {

    void importAttendances(Attendance[] attendances, Event event);

    List<Attendance> findAttendanceList(String eventId);

}
