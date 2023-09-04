package ar.com.semillero.semillatronalfa.models.project;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;


import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyData {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", nullable = false)
    @Setter(value = AccessLevel.NONE)
    private String id;

    private String ally;
    private String responsible;
    private String address;
    private String responsiblePhoneNumber;
    private String responsibleEmail;

    @OneToOne
    @JoinColumn(name = "project_id")
    @Getter(value = AccessLevel.NONE)
    @JsonIgnore
    @JsonManagedReference("project-mt")
    private Project project;
}
