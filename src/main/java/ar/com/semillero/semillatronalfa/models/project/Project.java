package ar.com.semillero.semillatronalfa.models.project;

import ar.com.semillero.semillatronalfa.models.seed.Seed;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
//@AllArgsConstructor
@NoArgsConstructor
public class Project implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", nullable = false)
//  @Setter(value = AccessLevel.NONE)
    private String id;

    private boolean isActive = true;
    private String projectName;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime timestamp;
    private String commission;
    private String production;
    private String originPlace;
    private String ods;

    @OneToOne(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference(value = "project-details")
    private ProjectDetails details;

    @OneToOne(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference(value = "management-team")
    private ManagementTeam managementTeam;

    @OneToOne(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference(value = "company-data")
    private CompanyData companyData;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    @Getter(value = AccessLevel.NONE)
    @JsonIgnore
    private List<ProjectSeed> projectSeed;

    @Builder
    public Project(String projectName, LocalDateTime timestamp, String commission,
            String production, String originPlace, String ods) {
        this.projectName = projectName;
        this.timestamp = timestamp;
        this.commission = commission;
        this.production = production;
        this.originPlace = originPlace;
        this.ods = ods;
    }
}
