package ar.com.semillero.semillatronalfa.entities.ally;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "ally_contact_data")
@NoArgsConstructor
@Data
public class AllyContactData {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", nullable = false)
    @Setter(value= AccessLevel.NONE)
    private String id;

    private String email;
    private String phoneNumber;
    private String address;
    @OneToOne
    @JoinColumn(name = "ally_id")
    @Getter(value= AccessLevel.NONE)
    @JsonIgnore
    private Ally ally;

    public AllyContactData(String email, String phoneNumber, String address) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
}
