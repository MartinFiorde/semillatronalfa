package ar.com.semillero.semillatronalfa.entities.project;

import java.util.Date;

import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class ProjectDataAbstract {

	private String name;
	private Date initialDate;
	private Date endDate;
	private Date estimatedTime;
	private String purpose;
	private String projectType;
	private String proyectStatus;
	private String projectStage;
	private String observations;

}