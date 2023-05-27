package ar.com.semillero.semillatronalfa.repositories.Proyect;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.semillero.semillatronalfa.entities.proyect.Project;

public interface ProjectDataRepository extends JpaRepository<Project, String> {

}
