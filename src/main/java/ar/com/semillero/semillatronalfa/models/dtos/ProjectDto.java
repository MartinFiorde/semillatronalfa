package ar.com.semillero.semillatronalfa.models.dtos;

import ar.com.semillero.semillatronalfa.models.dtos.SeedDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectDto implements Serializable {

    // PROJECT
    private String id;
    private String projectName;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime timestamp;
    private String commission;
    private String production;
    private String originPlace;
    private String ods;

    // PROJECT DETAILS
    private String purpose;
    private String projectType;
    private String projectStatus;
    private String projectStage;
    private String observations;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate initialDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endingDate;
    private String estimatedTime;

    // MANAGEMENT TEAM
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

    // COMPANY DATA
    private String ally;
    private String responsible;
    private String address;
    private String responsiblePhoneNumber;
    private String responsibleEmail;
    
    // SEEDS LIST
    private List<SeedDto> seeds;
}
