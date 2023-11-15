package ar.com.semillero.semillatronalfa.models.seed;

import ar.com.semillero.semillatronalfa.models.project.Project;
import ar.com.semillero.semillatronalfa.models.project.ProjectSeed;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

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

    @OneToMany(mappedBy = "seed", fetch = FetchType.LAZY)
    @Getter(value = AccessLevel.NONE)
    //@JsonIgnore
    private List<ProjectSeed> projectSeed;

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
