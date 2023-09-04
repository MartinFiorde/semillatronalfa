package ar.com.semillero.semillatronalfa.services.interfaces;

import ar.com.semillero.semillatronalfa.models.Attendance;
import ar.com.semillero.semillatronalfa.models.event.Event;

import java.util.List;

public interface AttendanceService {

    void importAttendances(List<Attendance> attendances, Event event);

    List<Attendance> findAttendanceList(String eventId);

}
