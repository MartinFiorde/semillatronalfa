package ar.com.semillero.semillatronalfa.controllers;

import ar.com.semillero.semillatronalfa.entities.event.Event;
import ar.com.semillero.semillatronalfa.services.event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/events")
public class EventController {

    @Autowired
    EventService eventService;

    @GetMapping("/event/{id}")
    @ResponseBody
    public Event findEventById(@PathVariable String id) {
        return eventService.findEventById(id);
    }

    @GetMapping("/list")
    @ResponseBody
    public List<Event> findEvents() {
        return eventService.findEventList();
    }

    @PostMapping("/create")
    @ResponseBody
    public void createEvent(@RequestBody Event event) {
        eventService.addEvent(event);
    }

    @PatchMapping("/event/{id}")
    @ResponseBody
    public void deleteEvent(@PathVariable String id) {
        eventService.deleteEvent(id);
    }
}
