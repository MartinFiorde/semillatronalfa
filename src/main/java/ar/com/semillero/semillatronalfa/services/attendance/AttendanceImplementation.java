package ar.com.semillero.semillatronalfa.services.attendance;

import ar.com.semillero.semillatronalfa.entities.attendance.Attendance;
import ar.com.semillero.semillatronalfa.entities.event.Event;
import ar.com.semillero.semillatronalfa.repositories.attendance.AttendanceRepository;
import ar.com.semillero.semillatronalfa.repositories.event.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceImplementation implements AttendanceService {

    @Autowired
    AttendanceRepository attendanceRepository;

    @Autowired
    EventRepository eventRepository;

    @Override
    public void importAttendances(Attendance[] attendances, Event event) {
        for ( Attendance attendance: attendances ) {
            event.getAttendance().add(attendance);
            attendance.setEvent(event);
        }
        eventRepository.save(event);
    }

    @Override
    public List<Attendance> findAttendanceList(String eventId) {
        return attendanceRepository.findEventAttendance(eventId);
    }
}
