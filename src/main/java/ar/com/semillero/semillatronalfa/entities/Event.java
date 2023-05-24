package ar.com.semillero.semillatronalfa.entities;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "event")
@NoArgsConstructor
@Data
public class Event {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", nullable = false)
    @Setter(value= AccessLevel.NONE)
    private String id;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;
    private String title;
    private Boolean offeredBySemillero;
    private String status;
    private String organizedBy;
    private String type;
    private String approach;

    @OneToOne(mappedBy = "event", cascade = CascadeType.ALL)
    private EventDetails details;

    public Event(LocalDate date, String title,
                 Boolean offeredBySemillero, String status,
                 String organizedBy, String approach,
                 String type, EventDetails details) {
        this.date = date;
        this.title = title;
        this.offeredBySemillero = offeredBySemillero;
        this.status = status;
        this.organizedBy = organizedBy;
        this.approach = approach;
        this.type = type;
        this.details = details;
    }
}
