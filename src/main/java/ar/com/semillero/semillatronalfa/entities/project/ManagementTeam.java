package ar.com.semillero.semillatronalfa.entities.project;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "managementTeam")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ManagementTeam {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "id", nullable = false)
	@Setter(value = AccessLevel.NONE)
	private String id;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "projectManager_id")
	private ProjectManager projectManager;
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "projectOwner_id")
	private ProjectOwner projectOwner;


}
