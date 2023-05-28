package ar.com.semillero.semillatronalfa.entities.proyect;

import ar.com.semillero.semillatronalfa.entities.ally.Ally;
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
	@OneToOne(mappedBy = "project", cascade = CascadeType.ALL)
	private ProjectData projectData;
	// private SeedData SeedData;
	//@ManyToOne(fetch = FetchType.LAZY)
	//@JoinColumn(name = "ally_id")
	//private Ally allyProject;

	/*public Project(ProjectData projectData, Ally allyProject) {
		this.projectData = projectData;
		this.allyProject = allyProject;
	}*/
}