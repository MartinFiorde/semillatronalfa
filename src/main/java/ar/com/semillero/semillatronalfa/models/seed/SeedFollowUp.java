package ar.com.semillero.semillatronalfa.models.seed;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import javax.persistence.CascadeType;
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
public class SeedFollowUp {

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

//    @Temporal(TemporalType.TIMESTAMP)
//    private Date postulationDate;
    private LocalDateTime postulationDate; // Reemplazo de la vieja clase Date: https://www.baeldung.com/migrating-to-java-8-date-time-api

    private String commission;

    @OneToOne(mappedBy = "seedFollowUpId", cascade = CascadeType.ALL)
    private SeedStatus status;

    // private Boolean recommendation;
    
    private String recommendationString;
    @Column(length = 1023)
    private String recommendationComment;

    // private Boolean certification;
    
    private String certificationString;
    @Column(length = 1023)
    private String observation;

//    @ElementCollection
//    @CollectionTable(name = "follow_up_comments", joinColumns = @JoinColumn(name = "id"))
//    @Column
//    private List<String> comments;

    public SeedFollowUp(LocalDateTime postulationDate, String commission, SeedStatus status, String recommendationString, String recommendationComment, String certificationString, String observation) {
        this.postulationDate = postulationDate;
        this.commission = commission;
        this.status = status;
        this.recommendationString = recommendationString;
        this.recommendationComment = recommendationComment;
        this.certificationString = certificationString;
        this.observation = observation;
    }


}
