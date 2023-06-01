package ar.com.semillero.semillatronalfa.controllers;

import ar.com.semillero.semillatronalfa.dtos.event.EventDto;
import ar.com.semillero.semillatronalfa.entities.event.Event;
import ar.com.semillero.semillatronalfa.services.event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/event")
public class EventController {

    @Autowired
    EventService eventService;

    @GetMapping("/{id}")
    @ResponseBody
    public EventDto findEventById(@PathVariable String id) {
        return eventService.findEventById(id);
    }

    @GetMapping("/list")
    @ResponseBody
    public List<EventDto> findEvents() {
        return eventService.findEventList();
    }

    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<Object> createEvent(@RequestBody Event event) {
            eventService.addEvent(event);
            return new ResponseEntity<>("Created successfully!", HttpStatus.CREATED);
    }

    @PostMapping("/import")
    @ResponseBody
    public ResponseEntity<Object> importEvent(@RequestBody Event[] event) {
        eventService.addEventList(event);
        return new ResponseEntity<>("Imported successfully!", HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    @ResponseBody
    public ResponseEntity<Object> updateEvent(@PathVariable("id") String id, @RequestBody Event event) {
        if(eventService.findEventById(id) != null && event != null) {
            eventService.updateEvent(event, eventService.findEvent(id));
            return new ResponseEntity<>("Updated successfully!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Error! Try a valid id or data.", HttpStatus.BAD_REQUEST);
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
