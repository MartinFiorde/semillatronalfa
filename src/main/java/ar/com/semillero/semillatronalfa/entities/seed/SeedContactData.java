package ar.com.semillero.semillatronalfa.entities.seed;

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
public class SeedContactData {

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

    private String email;
    
    private String telephone;
    
    private String linkedin;
    
    private String discordUser;

    public SeedContactData(String email, String telephone, String linkedin, String discordUser) {
        this.email = email;
        this.telephone = telephone;
        this.linkedin = linkedin;
        this.discordUser = discordUser;
    }

}
