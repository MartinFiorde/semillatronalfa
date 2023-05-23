package ar.com.semillero.semillatronalfa.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "event")
@NoArgsConstructor
@Data
@Getter
public class Event {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", nullable = false)
    @Setter(value= AccessLevel.NONE)
    private String id;

    private Date date;
    private String title;
    private Boolean offeredBySemillero;
    private String status;
    private String organizedBy;
    private String type;
    private String approach;

    @OneToOne(mappedBy = "event", cascade = CascadeType.ALL)
    private EventDetails details;

    public Event(Date date, String title,
                 Boolean offeredBySemillero, String status,
                 String organizedBy, String approach,
                 String type) {
        this.date = date;
        this.title = title;
        this.offeredBySemillero = offeredBySemillero;
        this.status = status;
        this.organizedBy = organizedBy;
        this.approach = approach;
        this.type = type;
    }
}
