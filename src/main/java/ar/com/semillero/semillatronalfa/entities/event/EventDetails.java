package ar.com.semillero.semillatronalfa.entities.event;

import ar.com.semillero.semillatronalfa.entities.event.Event;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@Entity
@Data
@NoArgsConstructor
public class EventDetails {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", nullable = false)
    @Setter(value= AccessLevel.NONE)
    private String id;

    private String instructor;
    private String description;
    private String location;
    private String origin;
    private String destination;
    private int duration;
    private String modality;
    private LocalTime startTime;
    private LocalTime endingTime;

    @OneToOne
    @JoinColumn(name = "event_id")
    @Getter(value= AccessLevel.NONE)
    @JsonIgnore
    private Event event;

    public EventDetails(String instructor, String description, String location, String origin, String destination, int duration, String modality) {
        this.instructor = instructor;
        this.description = description;
        this.location = location;
        this.origin = origin;
        this.destination = destination;
        this.duration = duration;
        this.modality = modality;
    }
    public EventDetails(String instructor, String description, String location, String origin, String destination, String modality, String startTime, String endingTime) {
        this.instructor = instructor;
        this.description = description;
        this.location = location;
        this.origin = origin;
        this.destination = destination;
        this.modality = modality;
        this.startTime = LocalTime.parse(startTime);
        this.endingTime = LocalTime.parse(endingTime);
    }
}
