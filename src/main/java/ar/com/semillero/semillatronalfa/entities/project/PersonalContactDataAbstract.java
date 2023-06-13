package ar.com.semillero.semillatronalfa.entities.project;



import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class PersonalContactDataAbstract {
	
	private String name;
	private String lastName;
	private String email;
	private String telephone;
	private String linkedin;
	private String discord;
}
