package ar.com.semillero.semillatronalfa.entities;

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

    @Column(name = "instructor")
    private String instructor;

    @Column(name = "origen")
    private String origin;

    @Column(name = "destinatarios")
    private String destination;

    @Column(name = "duracion")
    private double duration;

    @Column(name = "modalidad")
    private String modality;

    @OneToOne
    @JoinColumn(name = "event_id")
    private Event event;

    public EventDetails(String instructor, String origin, String destination, double duration, String modality, Event event) {
        this.instructor = instructor;
        this.origin = origin;
        this.destination = destination;
        this.duration = duration;
        this.modality = modality;
        this.event = event;
    }
}
