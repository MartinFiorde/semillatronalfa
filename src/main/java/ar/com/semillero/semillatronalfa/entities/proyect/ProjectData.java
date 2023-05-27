package ar.com.semillero.semillatronalfa.entities.proyect;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@Data
@Table(name = "projectData")
public class ProjectData {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "id", nullable = false)
	@Setter(value = AccessLevel.NONE)
	private String id;

	private String name;
	private Date initialDate;
	private Date endDate;
	private Date timestamp;
	private String purpose;
	private String ods;
	private String projectStatusStage;

//	   private Enum proyectStatus;
	// ejemplo de relacion => @OneToOne(mappedBy = "clase", cascade =
	// CascadeType.ALL)
	/*
	 * @OneToOne(mappedBy = "ally", cascade = CascadeType.ALL) private Ally ally;
	 */
//	
//	@OneToOne(mappedBy = "commission", cascade = CascadeType.ALL)
//	private Commission commission;
//	pendiente
//	   private ProjectManager projectManager;
//	   private ProjectOwner projectOwner;
	 @OneToOne
	    @JoinColumn(name = "project_id")
	    @Getter(value= AccessLevel.NONE)
	    @JsonIgnore
	    private Project project;
}
