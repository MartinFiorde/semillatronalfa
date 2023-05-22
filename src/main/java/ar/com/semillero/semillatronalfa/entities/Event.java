package ar.com.semillero.semillatronalfa.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "evento")
@NoArgsConstructor
@Data
public class Event {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", nullable = false)
    @Setter(value= AccessLevel.NONE)
    private String id;

    @Column(name = "fecha")
    private Date date;

    @Column(name = "titulo")
    private String title;

    @Column(name = "ofrecida_por_semillero")
    private Boolean offeredBy;

    @Column(name = "estado")
    private String status;

    @Column(name = "organizador")
    private String organizedBy;

    @Column(name = "tipo")
    private String type;

    @Column(name = "enfoque")
    private String approach;

    @OneToOne(mappedBy = "event", cascade = CascadeType.ALL)
    @JsonIgnore
    private EventDetails details;

    public Event(Date date, String title,
                 Boolean offeredBy, String status,
                 String organizedBy, String approach,
                 String type) {
        this.date = date;
        this.title = title;
        this.offeredBy = offeredBy;
        this.status = status;
        this.organizedBy = organizedBy;
        this.approach = approach;
        this.type = type;
    }


}
