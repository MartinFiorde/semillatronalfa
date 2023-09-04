package ar.com.semillero.semillatronalfa.models.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SeedDto {

    private String id;
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

    // PERSONAL DATA
    private String firstName;
    private String lastName;
    private Integer dni;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthDate;
    private String country;
    private String city;
    private String gender;

    // FOLLOW UP
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime postulationDate;
    private String commission;
    // private Boolean recommendation;
    private String recommendationString;
    private String recommendationComment;
    // private Boolean certification;
    private String certificationString;
    private String observation;

    // SEED STATUS
    private String primary;
    private String secondary;
    private String projectId;
    private String projectName;
    private Boolean digitalEmployment;
    private String companyName;
}
