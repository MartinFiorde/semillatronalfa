package ar.com.semillero.semillatronalfa.controllers;

import ar.com.semillero.semillatronalfa.configs.errors.EventClassException;
import ar.com.semillero.semillatronalfa.models.dtos.EventDto;
import ar.com.semillero.semillatronalfa.models.filters.EventFilter;
import ar.com.semillero.semillatronalfa.models.event.Event;
import ar.com.semillero.semillatronalfa.services.EventServiceImpl;
import ar.com.semillero.semillatronalfa.services.interfaces.EventService;
import ar.com.semillero.semillatronalfa.models.converters.EventConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.*;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//import javax.validation.Valid;
import java.io.IOException;
import java.util.*;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
@PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
@RequestMapping("/event")
public class EventController {

    EventServiceImpl eventService;

    EventConverter eventConverter;

    @Autowired
    public EventController(EventServiceImpl eventService, EventConverter eventConverter) {
        this.eventService = eventService;
        this.eventConverter = eventConverter;
    }

    @GetMapping("/{id}")
    public String findEventById(ModelMap modelMap, @PathVariable("id") String id, RedirectAttributes attributes) {
        String err = eventService.validateExistingEvent(id);
        if (!err.isEmpty()) {
            EventClassException ecexp = new EventClassException(err, HttpStatus.NOT_FOUND);
            ecexp.printStackTrace();
            modelMap.addAttribute("error", ecexp);
            return "error.html";
        }

        EventDto eventDto = eventConverter.entityToDto(eventService.findEvent(id));
        modelMap.addAttribute("event", eventDto);
        modelMap.addAttribute("attendance", eventDto.getAttendanceList());
        attributes.addFlashAttribute("Success", "¡Cargado con éxito!");
        return "pages/evento.html";
    }

    @GetMapping("/create-event")
    public String redirectToCreateEvent(ModelMap modelMap) {
        modelMap.addAttribute("event", new EventDto());
        return "pages/crear-evento.html";
    }

    @GetMapping("/edit-event/{id}")
    public String redirectToEditEvent(ModelMap modelMap, @PathVariable("id") String id) {
        String err = eventService.validateExistingEvent(id);
        if (!err.isEmpty()) {
            EventClassException ecexp = new EventClassException(err, HttpStatus.NOT_FOUND);
            ecexp.printStackTrace();
            modelMap.addAttribute("error", ecexp);
            return "error.html";
        }
        EventDto eventDto = eventService.findEventById(id);
        modelMap.addAttribute("event", eventDto);
        return "pages/editar-evento.html";
    }


    @GetMapping("/list")
    public String eventsView(Model model) {
        try {
            model.addAttribute("events", eventService.findEventList());
            model.addAttribute("eventFilters", new EventFilter());
            return "pages/eventos.html";
        } catch(Exception e) {
            e.printStackTrace();
            EventClassException ex = new EventClassException("Ha surgido un problema interno y no se ha podido ejecutar la petición."
                    , HttpStatus.INTERNAL_SERVER_ERROR);
            model.addAttribute("error", ex);
            return "error.html";
        }
    }

    @GetMapping("/list/filter")
    public String eventsViewFilter(Model model, @ModelAttribute EventFilter eventFilter) {
        try {
            model.addAttribute("events", eventService.filterEvent(eventFilter));
            model.addAttribute("eventFilters", eventFilter);
            return "pages/eventos.html";
        } catch(Exception e) {
            e.printStackTrace();
            EventClassException ex = new EventClassException("Ha surgido un problema interno y no se ha podido ejecutar la petición."
                    , HttpStatus.INTERNAL_SERVER_ERROR);
            model.addAttribute("error", ex);
            return "error.html";
        }
    }

    /*@GetMapping("/list/all")
    @PreAuthorize("permitAll()")
    @ResponseBody
    public List<EventDto> findEvents() {
        return eventService.findEventList();
    }*/

    @PreAuthorize("permitAll()")
    @Transactional
    @PostMapping("/create")
    public String createEvent( @ModelAttribute("event") EventDto eventDto, ModelMap modelMap, BindingResult result,
                              @RequestParam(value = "img", required = false) MultipartFile image, RedirectAttributes attributes) throws IOException {
        String err = eventService.validateEvent(eventDto);
        if (!err.isEmpty()) {
            EventClassException ecexp = new EventClassException(err, HttpStatus.NO_CONTENT);
            modelMap.addAttribute("error", ecexp);
            return "error.html";
        }
        if(result.hasErrors()) {
            return "pages/eventos.html";
        }
        Event event = eventConverter.dtoToEntity(eventDto);
        if(!image.isEmpty()) {
            String imgErr = eventService.validateImage(image);
            if (!imgErr.isEmpty()) {
                EventClassException exp = new EventClassException(imgErr, HttpStatus.NOT_ACCEPTABLE);
                modelMap.addAttribute("error", exp);
                return "error.html";
            }
           eventService.loadImage(image, event);
        }
        eventService.addEvent(event);
        attributes.addFlashAttribute("Success", "¡Creado con éxito!");
        return "redirect:/event/"+event.getId();
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/import")
    //@ResponseBody
    public void importEvent(@RequestBody List<EventDto> eventDtoList) {
        try {
            eventService.addEventList(eventDtoList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional
    @PostMapping("/load_image/{id}")
    public String loadImage(@RequestParam(value = "image", required = false) MultipartFile file,
                            @PathVariable("id") String eventId) throws IOException {
        Event event = eventService.findEvent(eventId);
        eventService.loadImage(file, event);
        eventService.addEvent(event);

        return "redirect:/evento/"+eventId;
    }

    @Transactional
    @PostMapping("/update/{id}")
    @PreAuthorize("permitAll()")
    public String updateEvent(Model model, @PathVariable("id") String id, @ModelAttribute EventDto eventDto,
                                              @RequestParam(value = "img", required = false) MultipartFile image,
                                              RedirectAttributes attributes) throws IOException {

        if(eventService.findEvent(id) != null) {
            Event event = eventService.findEvent(id);
            if(!image.isEmpty()) {
                eventService.loadImage(image, event);
            }
            eventService.updateEvent(eventDto, event);
            model.addAttribute("event", event);
            attributes.addFlashAttribute("Success", "¡Actualizado con éxito!");
            return "redirect:/event/"+ id;
        }
        attributes.addFlashAttribute("Failed", "Ha surgido un error, intente nuevamente.");
        return "redirect:/evento/list";
    }

    @PatchMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<Object> deleteEvent(@PathVariable("id") String id) {
        if(eventService.findEventById(id) != null) {
            eventService.deleteEvent(id);
            return new ResponseEntity<>("¡Eliminado con éxito!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/sheet")
    public ResponseEntity<Resource> downloadSheet() throws IOException {
        Resource resource = new ClassPathResource("static/assets/Plantilla_Eventos.xlsx");
        MediaType mediaType = MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        return ResponseEntity.ok()
                .contentType(mediaType)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + "Plantilla_Eventos.xlsx")
                .body(resource);
    }
}
