package ar.com.semillero.semillatronalfa.models.seed;

import ar.com.semillero.semillatronalfa.models.project.Project;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@NoArgsConstructor
public class Seed {

    @Id
    @GeneratedValue(generator = "uuid") //https://stackoverflow.com/questions/6356834/using-hibernate-uuidgenerator-via-annotations
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Setter(value = AccessLevel.NONE)
    @Column(nullable = false)
    private String id;

//    @ManyToOne
//    private Commission commission;
    
    @OneToOne(mappedBy = "seedId", cascade = CascadeType.ALL)
    private SeedFollowUp followUp;
    
    @OneToOne(mappedBy = "seedId", cascade = CascadeType.ALL)
    private SeedPersonalData personalData;

    @OneToOne(mappedBy = "seedId", cascade = CascadeType.ALL)
    private SeedContactData contactData;

    @OneToOne(mappedBy = "seedId", cascade = CascadeType.ALL)
    private SeedPostulationData postulationData;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "project_id")
//    @Getter(value = AccessLevel.NONE)
//    @JsonIgnore
//    private Project project;

    private boolean isActive = true;

    public Seed(SeedFollowUp followUp, SeedPersonalData personalData, SeedContactData contactData, SeedPostulationData postulationData) {
        this.followUp = followUp;
        this.personalData = personalData;
        this.contactData = contactData;
        this.postulationData = postulationData;
    }

}
