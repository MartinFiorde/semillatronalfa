package ar.com.semillero.semillatronalfa.repositories.project;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.semillero.semillatronalfa.entities.project.Project;

public interface ProjectDataRepository extends JpaRepository<Project, String> {

}