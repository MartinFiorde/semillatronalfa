package ar.com.semillero.semillatronalfa.models.seed;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class SeedStatus {

    @OneToOne
    @JoinColumn
    @Getter(value = AccessLevel.NONE)
    @JsonIgnore
    private SeedFollowUp seedFollowUpId;
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Setter(value = AccessLevel.NONE)
    @Column(nullable = false)
    private String id;

    @Column(name = "status_primary")
    private String primary;

    @Column(name = "status_secondary")
    private String secondary;
    
    private String projectId;
    
    private String projectName;

    private Boolean digitalEmployment;
    
    private String companyName;

    public SeedStatus(String primary, String secondary, String projectId, Boolean digitalEmployment, String companyName) {
        this.primary = primary;
        this.secondary = secondary;
        this.projectId = projectId;
        this.digitalEmployment = digitalEmployment;
        this.companyName = companyName;
    }


    
}
