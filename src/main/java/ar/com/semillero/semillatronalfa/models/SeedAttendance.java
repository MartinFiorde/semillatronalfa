package ar.com.semillero.semillatronalfa.models;

import ar.com.semillero.semillatronalfa.models.event.Event;
import ar.com.semillero.semillatronalfa.models.seed.Seed;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@NoArgsConstructor
public class SeedAttendance {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Setter(value = AccessLevel.NONE)
    @Column(nullable = false)
    private String id;

    private String eventId;

    private String eventTitle;

    private String seedId;

    private Long seedDni;

    private Boolean hasAttended;
    
    private boolean isActive = true;

    public SeedAttendance(Event event, Seed seed) {
        this.eventId = event.getId();
        this.eventTitle = event.getTitle();

        this.seedId = seed.getId();
        this.seedDni = (long) seed.getPersonalData().getDni();
        this.hasAttended = false;
    }

}
