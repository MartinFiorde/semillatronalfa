package ar.com.semillero.semillatronalfa.controllers;

import ar.com.semillero.semillatronalfa.entities.attendance.Attendance;
import ar.com.semillero.semillatronalfa.services.attendance.AttendanceService;
import ar.com.semillero.semillatronalfa.services.event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/attendance-list")
public class AttendanceController {

    @Autowired
    AttendanceService attendanceService;

    @Autowired
    EventService eventService;

    @PostMapping("/import/{id}")
    @ResponseBody
    public void importAttendanceList(@RequestBody Attendance[] attendances, @PathVariable("id") String eventId) {
        attendanceService.importAttendances(attendances, eventService.findEvent(eventId));
        //return "index.html";
    }

    @GetMapping("/list/{eventId}")
    @ResponseBody
    public List<Attendance> getAttendanceList(@PathVariable("eventId") String eventId) {
        return attendanceService.findAttendanceList(eventId);
    }
}
