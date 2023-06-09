package ar.com.semillero.semillatronalfa.services.project;

import java.util.List;

import ar.com.semillero.semillatronalfa.entities.project.Project;



public interface ProjectService {

	List<Project> findProjects();

	List<Project> findProjectList();

	void addProject(Project project);

	Project findProjectById(String id);

	void deleteProject(String id);
}