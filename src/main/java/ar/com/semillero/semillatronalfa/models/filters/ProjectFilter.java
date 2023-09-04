package ar.com.semillero.semillatronalfa.models.filters;

import java.time.LocalDate;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectFilter {

    private String searchBar;

    private String[] projectType;
    private String[] projectStatus;
    private String[] projectStage;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate initialDateFrom;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate initialDateTo;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endingDateFrom;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endingDateTo;
    private String[] commission;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate timestampFrom;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate timestampTo;

    public ProjectFilter(String searchBar) {
        this.searchBar = searchBar;
    }
}
