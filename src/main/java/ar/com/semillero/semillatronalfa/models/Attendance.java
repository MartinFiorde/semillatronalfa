package ar.com.semillero.semillatronalfa.models;

import ar.com.semillero.semillatronalfa.models.event.Event;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Data
public class Attendance implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", nullable = false)
    @Setter(value= AccessLevel.NONE)
    private String id;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    //@Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime timestamp;
    private String fullName;
    private Long dni;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    @Getter(value = AccessLevel.NONE)
    private Event event;

    public Attendance(LocalDateTime timestamp, String fullName, Long dni) {
        this.timestamp = timestamp;
        this.fullName = fullName;
        this.dni = dni;
    }
}
