package ar.com.semillero.semillatronalfa.dtos.event;

import ar.com.semillero.semillatronalfa.entities.event.Event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class EventDto {
    private String id;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;
    private String title;
    private Boolean offeredBySemillero;
    private String status;
    private String organizedBy;
    private String type;
    private String approach;
    private String instructor;
    private String origin;
    private String destination;
    private int duration;
    private String modality;

    public EventDto(Event event) {
        this.id = event.getId();
        this.date = event.getDate();
        this.title = event.getTitle();
        this.offeredBySemillero = event.getOfferedBySemillero();
        this.status = event.getStatus();
        this.organizedBy = event.getOrganizedBy();
        this.type = event.getType();
        this.approach = event.getApproach();
        this.instructor = event.getDetails().getInstructor();
        this.origin = event.getDetails().getOrigin();
        this.destination = event.getDetails().getDestination();
        this.duration = event.getDetails().getDuration();
        this.modality = event.getDetails().getModality();
    }
}
