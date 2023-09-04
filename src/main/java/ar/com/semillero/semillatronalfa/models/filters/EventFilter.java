package ar.com.semillero.semillatronalfa.models.filters;


import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventFilter {

    private String title;
    private String ally;
    private Boolean[] offeredBySemillero;
    private String[] status;
    private String organizedBy;
    private String[] type;
    private String[] approach;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fromDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate untilDate;
    private String[] origin;
    private String instructor;
    private String[] destination;
    private Integer duration;
    private String[] modality;
    private String description;
    private String location;
    private LocalTime startTime;
    private LocalTime endingTime;
    private String searchBar;
    private Integer totalAttendance;

    public EventFilter(String searchBar) {
        this.searchBar = searchBar;
    }

    public EventFilter(String title, String ally, Boolean[] offeredBySemillero,
                       String[] status, String organizedBy, String[] type,
                       String[] approach, LocalDate fromDate, LocalDate untilDate,
                       String[] origin, String instructor, String[] destination,
                       Integer duration, String[] modality, String description,
                       String location, LocalTime startTime, LocalTime endingTime, Integer totalAttendance) {
        this.title = title;
        this.ally = ally;
        this.offeredBySemillero = offeredBySemillero;
        this.status = status;
        this.organizedBy = organizedBy;
        this.type = type;
        this.approach = approach;
        this.fromDate = fromDate;
        this.untilDate = untilDate;
        this.origin = origin;
        this.instructor = instructor;
        this.destination = destination;
        this.duration = duration;
        this.modality = modality;
        this.description = description;
        this.location = location;
        this.startTime = startTime;
        this.endingTime = endingTime;
        this.totalAttendance = totalAttendance;
    }
}

