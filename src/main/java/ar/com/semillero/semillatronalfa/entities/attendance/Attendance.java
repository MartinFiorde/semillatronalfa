package ar.com.semillero.semillatronalfa.entities.attendance;

import ar.com.semillero.semillatronalfa.entities.event.Event;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@Data
public class Attendance {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", nullable = false)
    @Setter(value= AccessLevel.NONE)
    private String id;

    private Date timestamp;
    private String fullName;
    private Long dni;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    @Getter(value = AccessLevel.NONE)
    private Event event;

    public Attendance(Date timestamp, String fullName, Long dni) {
        this.timestamp = timestamp;
        this.fullName = fullName;
        this.dni = dni;
    }
}
