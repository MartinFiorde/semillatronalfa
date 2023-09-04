package ar.com.semillero.semillatronalfa.models.event;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
public class EventDetails implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", nullable = false)
    @Setter(value= AccessLevel.NONE)
    private String id;

    private String instructor;
    @Column(length = 1023)
    private String description;
    private String location;
    private String origin;
    private String destination;
    private int duration;
    private String modality;
    private LocalTime startTime;
    private LocalTime endingTime;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "event_id")
    @Getter(value= AccessLevel.NONE)
    @JsonIgnore
    //@JsonManagedReference
    private Event event;

    // Constructor para los Eventos a importar
    public EventDetails(String instructor, String description, String location, String origin, String destination, int duration, String modality) {
        this.instructor = instructor;
        this.description = description;
        this.location = location;
        this.origin = origin;
        this.destination = destination;
        this.duration = duration;
        this.modality = modality;
    }

    //Constructor para nuevos Eventos
    @Builder
    public EventDetails(String instructor, String description, String location, String origin, String destination, String modality, LocalTime startTime, LocalTime endingTime) {
        this.instructor = instructor;
        this.description = description;
        this.location = location;
        this.origin = origin;
        this.destination = destination;
        this.modality = modality;
        this.startTime = startTime;
        this.endingTime = endingTime;
    }
}
