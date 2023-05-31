package ar.com.semillero.semillatronalfa.entities.project;

<<<<<<< HEAD

=======
import ar.com.semillero.semillatronalfa.entities.ally.Ally;
import com.fasterxml.jackson.annotation.JsonIgnore;
>>>>>>> 0a6912da01cc84c80e7911db13822d9e21a919ce
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import ar.com.semillero.semillatronalfa.entities.ally.Ally;



import javax.persistence.*;

@Entity
@Table(name = "project")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Project extends ProjectDataAbstract{
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "id", nullable = false)
	@Setter(value = AccessLevel.NONE)
	private String id;
	
	private boolean isActive = true;
	
	/*NO LE ENCUENTRO SENTIDO TENER UNA RELACION CON COMISION 
	 * SI QUISIERA HACER UNA CONSULTA PARA DETERMINAR LAS VINCULACIONES ENTRE 
	 * COMISION Y PROYECTO  SE PUEDE USAR EL AÑO YA QUE AMBAS CUENTAN CON EL DATO AÑO*/	
//	private Commission commission; 
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ally_id")
	@JsonIgnore
	@Getter(value = AccessLevel.NONE)
	private Ally allyProject;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "managementTeam_id")
	private ManagementTeam managementTeam;
//	private DevelopmentTeam developmentTeam;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "developmentTeam")
	private DevelopmentTeam developmentTeam;

	

}