package ar.com.semillero.semillatronalfa.controllers;

import ar.com.semillero.semillatronalfa.models.Attendance;
import ar.com.semillero.semillatronalfa.models.dtos.EventDto;
import ar.com.semillero.semillatronalfa.models.event.Event;
import ar.com.semillero.semillatronalfa.services.AttendanceServiceImpl;
import ar.com.semillero.semillatronalfa.services.interfaces.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/attendance-list")
public class AttendanceController {

    @Autowired
    AttendanceServiceImpl attendanceService;

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
                                    RedirectAttributes attributes) throws NoSuchFieldException, IllegalAccessException {
        EventDto event = eventService.findEventById(eventId);
        modelMap.addAttribute("event", event);
        modelMap.addAttribute("attendances", attendanceService.findAttendanceListDto(eventId));
        attributes.addFlashAttribute("Success", "Loaded successfully");
        return "pages/asistentes.html";
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/sheet")
    public ResponseEntity<Resource> downloadSheet() throws IOException {
        Resource resource = new ClassPathResource("static/assets/Plantilla_Registro_Semillas_en_Eventos.xlsx");
        MediaType mediaType = MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        return ResponseEntity.ok()
                .contentType(mediaType)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + "Plantilla_Registro_Semillas_en_Eventos.xlsx")
                .body(resource);
    }
}
