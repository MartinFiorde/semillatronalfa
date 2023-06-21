package ar.com.semillero.semillatronalfa.queries.event;


import ar.com.semillero.semillatronalfa.entities.event.EventDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventFilter {

    private String title;
    private Boolean offeredBySemillero;
    private String status;
    private String organizedBy;
    private String type;
    private String approach;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;
    private String origin;
    private String instructor;
    private String destination;
    private Integer duration;
    private String modality;
    private String description;
    private String location;
    private LocalTime startTime;
    private LocalTime endingTime;

}
