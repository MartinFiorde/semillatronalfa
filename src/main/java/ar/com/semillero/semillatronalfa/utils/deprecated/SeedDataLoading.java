/*
package ar.com.semillero.semillatronalfa.models.dtos.seed;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SeedDataLoading implements Serializable {
    // PERSONAL DATA
    private String firstName;
    private String lastName;
    private Integer dni;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthDate;
    private String country;
    private String city;
    private String gender;
    
    // CONTACT DATA
    private String email;
    private String telephone;
    private String linkedin;
    private String discordUser;
    
    // POSTULATION DATA
    private String rol;
    private String turn;
    private String meetSemilleroBy;
    private String studies;
    private String hobbies;
    private String comment;
    
    // FOLLOW UP
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime postulationDate;
    private String commission;
    private Boolean recommendation;
    private Boolean certification;
    private String observation;
    
    // STATUS
    private String primary;
    private String secondary;
    private String projectId;
    private String projectName;
    private Boolean digitalEmployment;
    private String companyName;

}
*/