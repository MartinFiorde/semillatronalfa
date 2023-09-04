package ar.com.semillero.semillatronalfa.models.event;


import ar.com.semillero.semillatronalfa.models.Attendance;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "event")
@NoArgsConstructor
@Data
public class Event implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", nullable = false)
    //@Setter(value= AccessLevel.NONE)
    private String id;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;
    private String title;
    private Boolean offeredBySemillero;
    private String status;
    private String organizedBy;
    private String type;
    private String approach;
    private boolean isActive = true;
    private int totalAttendance = 0;

    @OneToOne(mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private EventDetails details;

    @OneToOne(mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Image image;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Attendance> attendance;

    @Builder
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

    public void setTotalAttendance() {
        this.totalAttendance += 1;
    }
}
