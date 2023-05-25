package ar.com.semillero.semillatronalfa.entities.ally;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ally")
@NoArgsConstructor
@Data
public class Ally {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", nullable = false)
    @Setter(value= AccessLevel.NONE)
    private String id;

    private String companyName;
    private String commission;
    //@Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    private String objective;
    private String companyRepresentative;
    @OneToOne(mappedBy = "ally", cascade = CascadeType.ALL)
    private AllyContactData contactData;

    public Ally(String companyName, String commission, Date timestamp,
                String objective, String companyRepresentative,
                AllyContactData contactData) {
        this.companyName = companyName;
        this.commission = commission;
        this.timestamp = timestamp;
        this.objective = objective;
        this.companyRepresentative = companyRepresentative;
        this.contactData = contactData;
    }
}
