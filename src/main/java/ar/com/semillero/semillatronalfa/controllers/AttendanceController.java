package ar.com.semillero.semillatronalfa.controllers;

import ar.com.semillero.semillatronalfa.models.Attendance;
import ar.com.semillero.semillatronalfa.models.event.Event;
import ar.com.semillero.semillatronalfa.services.interfaces.AttendanceService;
import ar.com.semillero.semillatronalfa.services.interfaces.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/attendance-list")
public class AttendanceController {

    @Autowired
    AttendanceService attendanceService;

    @Autowired
    EventService eventService;

    @PreAuthorize("permitAll()")
    @Transactional
    @PostMapping("/import/{id}")
    @ResponseBody
    public void importAttendanceList(@RequestBody List<Attendance> attendances,
                                       @PathVariable("id") String eventId) {
        Event event = eventService.findEvent(eventId);
        attendanceService.importAttendances(attendances, event);
    }
    @PreAuthorize("permitAll()")
    @GetMapping("/list/{eventId}")
    public String getAttendanceList(ModelMap modelMap, @PathVariable("eventId") String eventId,
                                    RedirectAttributes attributes) {
        Event event = eventService.findEvent(eventId);
        modelMap.addAttribute("event", event);
        modelMap.addAttribute("attendances", attendanceService.findAttendanceList(eventId));
        attributes.addFlashAttribute("Success", "Loaded successfully");
        return "pages/asistentes.html";
    }
}
