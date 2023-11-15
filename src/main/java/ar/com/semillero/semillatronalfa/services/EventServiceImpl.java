package ar.com.semillero.semillatronalfa.services;

import ar.com.semillero.semillatronalfa.models.dtos.EventDto;
import ar.com.semillero.semillatronalfa.models.filters.EventFilter;
import ar.com.semillero.semillatronalfa.models.converters.EventConverter;
import ar.com.semillero.semillatronalfa.repositories.*;
import ar.com.semillero.semillatronalfa.models.event.*;
import ar.com.semillero.semillatronalfa.models.SeedAttendance;
import ar.com.semillero.semillatronalfa.models.converters.SeedConverter;
import ar.com.semillero.semillatronalfa.repositories.queries.EventQueries;
import ar.com.semillero.semillatronalfa.models.seed.Seed;
import ar.com.semillero.semillatronalfa.repositories.SeedAttendanceRepository;
import ar.com.semillero.semillatronalfa.services.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.IOException;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    private SeedService seedService;
    @Autowired
    private SeedConverter seedConverter;
    
    @Autowired 
    private SeedAttendanceRepository seedAttendanceRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    EventConverter eventMethods;

    @Override
    public void loadImage(MultipartFile image, Event event) throws IOException {
        Image img = new Image();
        img.setName(image.getOriginalFilename());
        img.setType(image.getContentType());
        img.setSize(image.getSize());
        img.setBytes(image.getBytes());
        event.setImage(img);
        img.setEvent(event);
    }
    public String validateImage(MultipartFile image) throws IOException {
        String message = "";
        if(!image.isEmpty()) {
            if(!image.getOriginalFilename().toLowerCase().endsWith(".jpg") && !image.getOriginalFilename().toLowerCase().endsWith(".jpeg")
                    && !image.getOriginalFilename().toLowerCase().endsWith(".png")){
                message = "El formato del archivo no está permitido. Intente con .jpg, .jpeg, .png.";
            }
        }
        return message;
    }

    public String validateEvent(EventDto event) {
        String message = "";
        if(event != null) {
            if(event.getTitle() == null || event.getOfferedBySemillero() == null || event.getOrganizedBy() == null ||
            event.getType() == null || event.getDate() == null || event.getApproach() == null || event.getStatus() == null ||
            event.getDestination() == null || event.getInstructor() == null ||
            event.getOrigin() == null || event.getStartTime() == null ||
                    event.getEndingTime() == null || event.getModality() == null) {
                message = "Uno o más campos obligatorios no fueron completados.";
            }
        } else {
            message = "Ha surgido un problema al crear el evento";
        }
        return message;
    }

    public String validateExistingEvent(String eventId) {
        String message = "";
        EventDto event = findEventById(eventId);
        if(event == null) {
            message = "El evento con el id solicitado no se encontró.";
        }
        return message;
    }

    private final void setBase64(EventDto eventDto) {
        if(eventDto != null) {
            String base64Image = Base64.getEncoder().encodeToString(eventDto.getImage().getBytes());
            eventDto.setBase64(base64Image);
        }
    }

    @Override
    public List<EventDto> findEventList() {
        List<EventDto> eventDtoList = eventMethods.entitiesToDto(eventRepository.getEventList());
        return eventDtoList;
    }

    @Override
    public Event addEvent(Event event) {
        Event newEvent = null;
        if(event != null) {
            newEvent = eventRepository.save(event);
            generateSeedAttendanceForNewEvent(newEvent); // MAF: el metodo tiene que pasar despues del save, para que el ID este creado
        }
        return newEvent;
    }

    @Override
    public EventDto findEventById(String id) {
        EventDto eventDto = eventMethods.entityToDto(eventRepository.findEventById(id).orElse(null));
        return eventDto;
    }

    @Override
    public void deleteEvent(String id) {
        Event event = eventRepository.findById(id).orElse(null);
        if (event == null) throw new AssertionError();
        event.setActive(false);
        eventRepository.save(event);
    }

    @Override
    public Event findEvent(String id) {
        return eventRepository.findEventById(id).orElse(null);
    }

    @Override
    public Event updateEvent(EventDto eventDto, Event event) {
        if(event != null && eventDto != null) {
            event.setStatus(eventDto.getStatus());
            event.setTitle(eventDto.getTitle());
            event.setApproach(eventDto.getApproach());
            event.setDate(eventDto.getDate());
            event.setOfferedBySemillero(eventDto.getOfferedBySemillero());
            event.setOrganizedBy(eventDto.getOrganizedBy());
            event.setType(eventDto.getType());
            if(event.getDetails() != null) {
                event.getDetails().setDescription(eventDto.getDescription());
                event.getDetails().setDestination(eventDto.getDestination());
                event.getDetails().setInstructor(eventDto.getInstructor());
                event.getDetails().setLocation(eventDto.getLocation());
                event.getDetails().setModality(eventDto.getModality());
                event.getDetails().setOrigin(eventDto.getOrigin());
                event.getDetails().setStartTime(eventDto.getStartTime());
                event.getDetails().setEndingTime(eventDto.getEndingTime());
                event.getDetails().setDuration((int) ChronoUnit.MINUTES.between(eventDto.getStartTime(), eventDto.getEndingTime()));
            }
            eventRepository.save(event);
        }

        return event;
    }

    @Override
    public void addEventList(List<EventDto> eventDtoList) {
        eventDtoList.forEach(eventDto -> {
            eventRepository.save(eventMethods.dtoToEntity(eventDto));
        });
    }

    @Override
    public List<Event> findEventListA() {
        return eventRepository.getEventList();
    }

    @Override
    public List<EventDto> filterEvent(EventFilter eventFilter) {
        List<Event> events = entityManager.createNativeQuery(
                //eventFilter.getSearchBar() != null || eventFilter.getSearchBar().isEmpty() ? EventQueries.searchBarQuery(eventFilter) :
                        EventQueries.filterEvent(eventFilter)
                        , Event.class).getResultList();
        List<EventDto> eventDtoList = eventMethods.entitiesToDto(events);
        return eventDtoList;
    }

    public void generateSeedAttendanceForNewEvent (Event event){
        List<Seed> seeds = seedConverter.dtosToEntities(seedService.orderedSeedList(Optional.of("asc"), Optional.of("asc")));
        List<SeedAttendance> seedAttandances = new ArrayList();
        seeds.stream().filter((seed) -> (seed.getPersonalData().getDni()!= null)).forEachOrdered((seed) -> {seedAttandances.add(new SeedAttendance(event, seed));});
//        MISMA FUNCIONALIDAD QUE EL STREAM DE ARRIBA
//        for (Seed seed : seeds) {
//            if(seed.getPersonalData().getDni()!= null) {
//                seedAttandances.add(new SeedAttendance(event, seed));
//            }
//        }
        seedAttendanceRepository.saveAll(seedAttandances);
    }
}