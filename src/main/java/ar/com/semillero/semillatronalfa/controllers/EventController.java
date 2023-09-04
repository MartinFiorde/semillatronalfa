package ar.com.semillero.semillatronalfa.controllers;

import ar.com.semillero.semillatronalfa.models.dtos.EventDto;
import ar.com.semillero.semillatronalfa.models.filters.EventFilter;
import ar.com.semillero.semillatronalfa.models.event.Event;
import ar.com.semillero.semillatronalfa.services.interfaces.EventService;
import ar.com.semillero.semillatronalfa.models.converters.EventConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.*;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
@PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
@RequestMapping("/event")
public class EventController {

    @Autowired
    EventService eventService;

    @Autowired
    EventConverter eventConverter;



    @GetMapping("/{id}")
    public String findEventById(ModelMap modelMap, @PathVariable("id") String id, RedirectAttributes attributes) {

        EventDto eventDto = eventConverter.entityToDto(eventService.findEvent(id));
        modelMap.addAttribute("event", eventDto);
        modelMap.addAttribute("attendance", eventDto.getAttendanceList());
        attributes.addFlashAttribute("Success", "Loaded Successfully");
        return "pages/evento.html";
    }

    @GetMapping("/create-event")
    public String redirectToCreateEvent(ModelMap modelMap) {
        modelMap.addAttribute("event", new EventDto());
        return "pages/crear-evento.html";
    }

    @GetMapping("/edit-event/{id}")
    public String redirectToEditEvent(ModelMap modelMap, @PathVariable("id") String id) {
        EventDto eventDto = eventService.findEventById(id);
        modelMap.addAttribute("event", eventDto);
        return "pages/editar-evento.html";
    }


    @GetMapping("/list")
    public String eventsView(Model model) {
        model.addAttribute("events", eventService.findEventList());
        model.addAttribute("eventFilters", new EventFilter());
        return "pages/eventos.html";
    }

    @GetMapping("/list/filter")
    public String eventsViewFilter(Model model, @ModelAttribute EventFilter eventFilter) {
        model.addAttribute("events", eventService.filterEvent(eventFilter));
        model.addAttribute("eventFilters", eventFilter);
        return "pages/eventos.html";
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
    public String createEvent(@Valid @ModelAttribute("event") EventDto eventDto, ModelMap modelMap, BindingResult result,
                              @RequestParam(value = "img", required = false) MultipartFile image, RedirectAttributes attributes) throws IOException {
        if(result.hasErrors()) {
            return "pages/eventos.html";
        }
        Event event = eventConverter.dtoToEntity(eventDto);
        if(!image.isEmpty()) {
           eventService.loadImage(image, event);
        }
        eventService.addEvent(event);
        attributes.addFlashAttribute("Success", "Creado con éxito");
        return "redirect:/event/"+event.getId();
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/import")
    //@ResponseBody
    public void importEvent(@RequestBody List<EventDto> eventDtoList) {
        eventService.addEventList(eventDtoList);
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
            attributes.addFlashAttribute("Success", "Uploaded Successfully");
            return "redirect:/event/"+ id;
        }
        attributes.addFlashAttribute("Failed", "Try again");
        return "redirect:/evento/list";
    }

    @PatchMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<Object> deleteEvent(@PathVariable("id") String id) {
        if(eventService.findEventById(id) != null) {
            eventService.deleteEvent(id);
            return new ResponseEntity<>("Removed successfully!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
    }
}
