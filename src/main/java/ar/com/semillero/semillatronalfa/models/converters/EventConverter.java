package ar.com.semillero.semillatronalfa.models.converters;

import ar.com.semillero.semillatronalfa.models.dtos.EventDto;
import ar.com.semillero.semillatronalfa.models.event.*;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Component;

import java.time.temporal.ChronoUnit;
import java.util.*;

@Component
public class EventConverter extends Converter<Event, EventDto> {

    public EventConverter() {
        super();
    }

    public static final void setBase64(EventDto eventDto) {
        if(eventDto.getImage() != null) {
            String base64Image = Base64.getEncoder().encodeToString(eventDto.getImage().getBytes());
            eventDto.setBase64(base64Image);
        }
    }

    @Override
    public EventDto entityToDto(Event event) {
        EventDto eventDto = modelMapper.map(event, EventDto.class);

        eventDto.setInstructor(event.getDetails().getInstructor());
        eventDto.setDescription(event.getDetails().getDescription());
        eventDto.setLocation(event.getDetails().getLocation());
        eventDto.setOrigin(event.getDetails().getOrigin());
        eventDto.setDestination(event.getDetails().getDestination());
        eventDto.setDuration(event.getDetails().getDuration());
        eventDto.setModality(event.getDetails().getModality());
        eventDto.setStartTime(event.getDetails().getStartTime());
        eventDto.setEndingTime(event.getDetails().getEndingTime());
        eventDto.setDuration((int) ChronoUnit.MINUTES.between(event.getDetails().getStartTime(), event.getDetails().getEndingTime()));
        if(event.getImage() != null) {
            setBase64(eventDto);
        }
        eventDto.setAttendanceList(event.getAttendance());
        return eventDto;
    }

    @Override
    public Event dtoToEntity(EventDto eventDto) throws ParseException {
        Event event = modelMapper.map(eventDto, Event.class);
        EventDetails eventDetails = modelMapper.map(eventDto, EventDetails.class);
        event.setDetails(eventDetails);
        eventDetails.setEvent(event);
        event.getDetails().setDuration((int) ChronoUnit.MINUTES.between(eventDto.getStartTime(), eventDto.getEndingTime()));

        return event;
    }


    public Event updateDtoToEntity(EventDto eventDto, Event event) throws ParseException {
        event.setStatus(eventDto.getStatus());
        event.setTitle(eventDto.getTitle());
        event.setApproach(eventDto.getApproach());
        event.setDate(eventDto.getDate());
        event.setOfferedBySemillero(eventDto.getOfferedBySemillero());
        event.setOrganizedBy(eventDto.getOrganizedBy());
        event.setType(eventDto.getType());
        event.getDetails().setDescription(eventDto.getDescription());
        event.getDetails().setDestination(eventDto.getDestination());
        event.getDetails().setInstructor(eventDto.getInstructor());
        event.getDetails().setLocation(eventDto.getLocation());
        event.getDetails().setModality(eventDto.getModality());
        event.getDetails().setOrigin(eventDto.getOrigin());
        event.getDetails().setStartTime(eventDto.getStartTime());
        event.getDetails().setEndingTime(eventDto.getEndingTime());
        event.getDetails().setDuration((int) ChronoUnit.MINUTES.between(eventDto.getStartTime(), eventDto.getEndingTime()));

        return event;
    }

    @Override
    public List<EventDto> entitiesToDto(List<Event> events) {
        return super.entitiesToDto(events);
    }

    @Override
    public List<Event> dtosToEntities(List<EventDto> eventDtoList) {
        return super.dtosToEntities(eventDtoList);
    }


}
