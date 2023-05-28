package ar.com.semillero.semillatronalfa.entities.project;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;


import javax.persistence.*;

@Entity
@Data
@Table(name = "project")
@NoArgsConstructor

public class Project {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "id", nullable = false)
	@Setter(value = AccessLevel.NONE)
	private String id;
	
	private boolean isActive = true;
	
	
	@OneToOne(mappedBy = "project", cascade = CascadeType.ALL)
	private ProjectData projectData;
	// private SeedData SeedData;


	public Project(String id, boolean isActive, ProjectData projectData) {
		super();
		this.id = id;
		this.isActive = isActive;
		this.projectData = projectData;
	}
	
	
}