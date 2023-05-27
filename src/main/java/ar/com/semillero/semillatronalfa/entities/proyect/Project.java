package ar.com.semillero.semillatronalfa.entities.proyect;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

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
}
