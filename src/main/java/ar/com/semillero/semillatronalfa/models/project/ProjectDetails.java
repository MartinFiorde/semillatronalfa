package ar.com.semillero.semillatronalfa.models.project;

import javax.persistence.*;


import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDetails implements Serializable {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "id", nullable = false)
	@Setter(value = AccessLevel.NONE)
	private String id;
	@Column(length = 1023)
	private String purpose;
	private String projectType;
	private String projectStatus;
	private String projectStage;
        @Column(length = 1023)
	private String observations;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate initialDate;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate endingDate;
	private String estimatedTime;
	@OneToOne
	@JoinColumn(name = "project_id")
	@Getter(value= AccessLevel.NONE)
	@JsonIgnore
	@JsonManagedReference("project-details")
	private Project project;
	// CONSTRUCTOR PARA MODIFICACIÓN DE PROJECTO
	@Builder
	public ProjectDetails(String purpose, String projectType, String projectStatus,
						  String projectStage, String observations, LocalDate initialDate,
						  LocalDate endingDate, String estimatedTime) {
		this.purpose = purpose;
		this.projectType = projectType;
		this.projectStatus = projectStatus;
		this.projectStage = projectStage;
		this.observations = observations;
		this.initialDate = initialDate;
		this.endingDate = endingDate;
		this.estimatedTime = estimatedTime;
	}

	@Builder
	public ProjectDetails(String purpose, String projectType,
						  String projectStatus, String projectStage,
						  LocalDate initialDate, LocalDate endingDate) {
		this.purpose = purpose;
		this.projectType = projectType;
		this.projectStatus = projectStatus;
		this.projectStage = projectStage;
		this.initialDate = initialDate;
		this.endingDate = endingDate;
	}


}