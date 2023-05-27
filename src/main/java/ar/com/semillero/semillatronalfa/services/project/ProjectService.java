package ar.com.semillero.semillatronalfa.services.project;

import java.util.List;

import ar.com.semillero.semillatronalfa.entities.proyect.Project;



public interface ProjectService {

	List<Project> findProjects();

	List<Project> findProjectList();

	void addProject(Project event);

	Project findProjectById(String id);

	void deleteProject(String id);
}
