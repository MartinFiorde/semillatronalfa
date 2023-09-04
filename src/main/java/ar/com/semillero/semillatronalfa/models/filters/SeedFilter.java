package ar.com.semillero.semillatronalfa.models.filters;

import lombok.Data;

import java.time.LocalDateTime;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeedFilter {
    private String searchBar;
    private String firstName;
    private String lastName;
    private String[] country;
    private String[] rol;
    private String[] gender;
    private String[] turn;
    private String[] commission;
    private String[] recommendationString;
    private boolean isRecommended;
    private String[] status;
    private String[] statusSecundary;
    private String[] certificationString;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime fromDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime untilDate;
    private String[] assignedProject;

    public SeedFilter(String searchBar) {
        this.searchBar = searchBar;
    }

    public SeedFilter(String firstName, String lastName,
                      String[] country, String[] rol, String[] gender,
                      String[] turn, String[] commission, boolean isRecommended,
                      String[] status, String[]statusSecundary, String[] certificationString,
                      LocalDateTime fromDate, LocalDateTime untilDate,String[] assignedProject,
                      String[] recommendationString) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.rol = rol;
        this.gender = gender;
        this.turn = turn;
        this.commission = commission;
        this.isRecommended = isRecommended;
        this.status = status;
        this.fromDate = fromDate;
        this.untilDate = untilDate;
        this.assignedProject = assignedProject;
        this.recommendationString = recommendationString;
        this.statusSecundary = statusSecundary;
        this.certificationString = certificationString;
    }
}
