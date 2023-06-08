package ar.com.semillero.semillatronalfa.entities.seed;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
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
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Data
@NoArgsConstructor
public class SeedPersonalData {

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

    private String firstName;

    private String lastName;

    private Integer dni;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthDate; // Reemplazo de la vieja clase Date: https://www.baeldung.com/migrating-to-java-8-date-time-api

    private String country;

    private String city;

    private String gender;
    
    public SeedPersonalData(String firstName, String lastName, Integer dni, LocalDate birthDate, String country, String city, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dni = dni;
        this.birthDate = birthDate;
        this.country = country;
        this.city = city;
        this.gender = gender;
    }

}
