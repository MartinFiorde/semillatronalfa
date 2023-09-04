package ar.com.semillero.semillatronalfa.services;

import ar.com.semillero.semillatronalfa.repositories.SeedRepository;
import ar.com.semillero.semillatronalfa.repositories.SeedAttendanceRepository;
import ar.com.semillero.semillatronalfa.services.interfaces.AttendanceService;
import ar.com.semillero.semillatronalfa.models.SeedAttendance;
import ar.com.semillero.semillatronalfa.models.Attendance;
import ar.com.semillero.semillatronalfa.models.event.Event;
import ar.com.semillero.semillatronalfa.repositories.AttendanceRepository;
import ar.com.semillero.semillatronalfa.repositories.EventRepository;
import ar.com.semillero.semillatronalfa.services.interfaces.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    AttendanceRepository attendanceRepository;

    @Autowired
    EventRepository eventRepository;
    @Autowired
    SeedService seedService;
    @Autowired
    SeedAttendanceRepository seedAttendanceRepository;
    @Autowired
    SeedRepository seedRepository;

    @Override
    public void importAttendances(List<Attendance> attendances, Event event) {
        for (Attendance attendance : attendances) {
            event.setTotalAttendance();
            attendance.setEvent(event);
            attendanceRepository.save(attendance);
            updateSeedAttendance(attendance.getDni(), event.getId());
        }
        eventRepository.save(event);
    }

    @Override
    public List<Attendance> findAttendanceList(String eventId) {
        return attendanceRepository.findEventAttendance(eventId);
    }

    public void updateSeedAttendance(Long seedDni, String eventId) {
        System.out.println("------------------ENTRO AL METODO------------------");
        SeedAttendance seedAttendance = seedAttendanceRepository.getSeedAttendanceBySeedDniAndEventId(seedDni.intValue(), eventId).orElse(null);
        
        if (seedAttendance != null) {
            
            System.out.println("------------------ENTRO A PONER ASISTENCIA------------------");
            System.out.println("------------------SEED ID"+seedAttendance.getSeedId()+"------------------");
            seedAttendance.setHasAttended(true);
            seedAttendanceRepository.save(seedAttendance);
        }
    }
}
