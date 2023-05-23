package ar.com.semillero.semillatronalfa.controllers;

import ar.com.semillero.semillatronalfa.entities.Event;
import ar.com.semillero.semillatronalfa.services.event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/events")
public class EventController {

    @Autowired
    EventService eventService;

    @GetMapping("/list")
    @ResponseBody
    public List<Event> findEvents() {
        return eventService.findEvents();
    }

}
