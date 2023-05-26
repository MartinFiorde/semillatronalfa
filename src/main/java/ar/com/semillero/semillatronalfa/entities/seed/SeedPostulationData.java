package ar.com.semillero.semillatronalfa.entities.seed;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@NoArgsConstructor
public class SeedPostulationData {

    @OneToOne
    @JoinColumn
    @Getter(value = AccessLevel.NONE)
    @JsonIgnore
    private Seed seedId;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Setter(value = AccessLevel.NONE)
    @Column(nullable = false)
    private String id;

    private String rol;

    private String turn;

    private String meetSemilleroBy;
    
    private String studies;
    
    private String hobbies;
    
    private String comment;
    
    public SeedPostulationData(String rol, String turn, String meetSemilleroBy, String studies, String hobbies, String comment) {
        this.rol = rol;
        this.turn = turn;
        this.meetSemilleroBy = meetSemilleroBy;
        this.studies = studies;
        this.hobbies = hobbies;
        this.comment = comment;
    }

    
}
