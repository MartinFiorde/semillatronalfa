package ar.com.semillero.semillatronalfa.entities.seed;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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

    private Boolean recommendation;

    private Boolean certification;

    @ElementCollection
    @CollectionTable(name = "follow_up_comments", joinColumns = @JoinColumn(name = "id"))
    @Column
    private List<String> comments;

    public SeedFollowUp(LocalDateTime postulationDate, SeedStatus status, String commission, Boolean recommendation, Boolean certification, List<String> comments) {
        this.postulationDate = postulationDate;
        this.commission = commission;
        this.status = status;
        this.recommendation = recommendation;
        this.certification = certification;
        this.comments = comments;
    }

}
