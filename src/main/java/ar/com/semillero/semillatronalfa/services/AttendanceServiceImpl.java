package ar.com.semillero.semillatronalfa.services;

import ar.com.semillero.semillatronalfa.models.dtos.AttendanceDto;
import ar.com.semillero.semillatronalfa.repositories.SeedRepository;
import ar.com.semillero.semillatronalfa.repositories.SeedAttendanceRepository;
import ar.com.semillero.semillatronalfa.services.interfaces.AttendanceService;
import ar.com.semillero.semillatronalfa.models.SeedAttendance;
import ar.com.semillero.semillatronalfa.models.Attendance;
import ar.com.semillero.semillatronalfa.models.event.Event;
import ar.com.semillero.semillatronalfa.repositories.AttendanceRepository;
import ar.com.semillero.semillatronalfa.repositories.EventRepository;
import ar.com.semillero.semillatronalfa.services.interfaces.SeedService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
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
            updateSeedAttendance(attendance.getDni(), event);
        }
        eventRepository.save(event);
    }

    @Override
    public List<Attendance> findAttendanceList(String eventId) {
        return attendanceRepository.findEventAttendance(eventId);
    }

    @Override
    public List<AttendanceDto> findAttendanceListDto(String eventId) {
        List<AttendanceDto> attendances = new ArrayList<>();
        try {
            List<Object[]> rawAttendances = attendanceRepository.findEventAttendanceDto(eventId);

            for (Object[] ra : rawAttendances) {
                String fullName = String.valueOf(ra[0]);
                Long dni = Long.valueOf(ra[1].toString());
                String timestamp = String.valueOf(ra[2]);

                AttendanceDto attendance = new AttendanceDto();
                attendance.setDni(dni);
                attendance.setFullName(fullName);
                attendance.setTimestamp(timestamp);
                attendances.add(attendance);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return attendances;
    }

    public void updateSeedAttendance(Long seedDni, Event event) {
        SeedAttendance seedAttendance = seedAttendanceRepository.getSeedAttendanceBySeedDniAndEventId(seedDni.intValue(), event.getId()).orElse(null);
        if (seedAttendance != null) {
            seedAttendance.setHasAttended(true);
            seedAttendanceRepository.save(seedAttendance);
        } else if (!seedRepository.getSeedByDni(seedDni.intValue()).isEmpty()) {
            SeedAttendance newSeedAttendance = new SeedAttendance(event, seedRepository.getSeedByDni(seedDni.intValue()).get(0));
            newSeedAttendance.setHasAttended(true);
            seedAttendanceRepository.save(newSeedAttendance);
        }
    }
}
