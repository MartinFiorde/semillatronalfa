package ar.com.semillero.semillatronalfa.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class EventDetails {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", nullable = false)
    @Setter(value= AccessLevel.NONE)
    private String id;

    private String instructor;
    private String origin;
    private String destination;
    private int duration;
    private String modality;

    @OneToOne
    @JoinColumn(name = "event_id")
    @Getter(value= AccessLevel.NONE)
    @JsonIgnore
    private Event event;

    public EventDetails(String instructor, String origin, String destination, int duration, String modality, Event event) {
        this.instructor = instructor;
        this.origin = origin;
        this.destination = destination;
        this.duration = duration;
        this.modality = modality;
        this.event = event;
    }

}
