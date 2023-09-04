package ar.com.semillero.semillatronalfa.models.project;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManagementTeam implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", nullable = false)
    @Setter(value = AccessLevel.NONE)
    private String id;
    
    private String productOwnerFullName;
    private String productOwnerEmail;
    private String productOwnerTelephone;
    private String productOwnerDiscordUser;

    private String projectManagerFullName;
    private String projectManagerEmail;
    private String projectManagerTelephone;
    private String projectManagerDiscordUser;

    private String teamLeaderFullName;
    private String teamLeaderEmail;
    private String teamLeaderTelephone;
    private String teamLeaderDiscordUser;

    @OneToOne
    @JoinColumn(name = "project_id")
    @Getter(value = AccessLevel.NONE)
    @JsonIgnore
    @JsonManagedReference("project-mt")
    private Project project;

    //@Builder
    public ManagementTeam(String productOwnerFullName, String productOwnerEmail, String productOwnerTelephone, String productOwnerDiscordUser, String projectManagerFullName, String projectManagerEmail, String projectManagerTelephone, String projectManagerDiscordUser, String teamLeaderFullName, String teamLeaderEmail, String teamLeaderTelephone, String teamLeaderDiscordUser) {
        this.productOwnerFullName = productOwnerFullName;
        this.productOwnerEmail = productOwnerEmail;
        this.productOwnerTelephone = productOwnerTelephone;
        this.productOwnerDiscordUser = productOwnerDiscordUser;
        this.projectManagerFullName = projectManagerFullName;
        this.projectManagerEmail = projectManagerEmail;
        this.projectManagerTelephone = projectManagerTelephone;
        this.projectManagerDiscordUser = projectManagerDiscordUser;
        this.teamLeaderFullName = teamLeaderFullName;
        this.teamLeaderEmail = teamLeaderEmail;
        this.teamLeaderTelephone = teamLeaderTelephone;
        this.teamLeaderDiscordUser = teamLeaderDiscordUser;
    }
}
