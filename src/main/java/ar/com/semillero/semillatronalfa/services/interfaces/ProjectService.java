package ar.com.semillero.semillatronalfa.services.interfaces;

import ar.com.semillero.semillatronalfa.models.dtos.ProjectDto;
import ar.com.semillero.semillatronalfa.models.filters.ProjectFilter;
import java.util.List;

import ar.com.semillero.semillatronalfa.models.project.Project;



public interface ProjectService {

	List<Project> findProjects();

	List<ProjectDto> findProjectList();

	List<ProjectDto> filteredProjects(ProjectFilter projectFilter);

	void addProject(Project project);

	void importProjects(List<ProjectDto> projectDtoList);

	Project findProjectById(String id);
        
	ProjectDto findProjectByIdDto(String id);

	void deleteProject(String id);

	ProjectDto updateProject(ProjectDto project);

	void addObservation(String id, String observation);
}