package ar.com.semillero.semillatronalfa.services.interfaces;

import ar.com.semillero.semillatronalfa.models.dtos.ProjectDto;
import ar.com.semillero.semillatronalfa.models.dtos.ProjectSeedDto;
import ar.com.semillero.semillatronalfa.models.dtos.SeedDto;
import ar.com.semillero.semillatronalfa.models.filters.ProjectFilter;
import java.util.List;

import ar.com.semillero.semillatronalfa.models.project.Project;
import ar.com.semillero.semillatronalfa.models.project.ProjectSeed;
import org.springframework.data.repository.query.Param;


public interface ProjectService {

	List<Project> findProjects();

	List<ProjectDto> findProjectList();

	List<ProjectDto> filteredProjects(ProjectFilter projectFilter);

	void addProject(Project project);

	void importProjects(List<ProjectDto> projectDtoList);

	Project findProjectById(String id);

	Project findProjectByName(String projectName);
        
	ProjectDto findProjectByIdDto(String id);

	void deleteProject(String id);

	ProjectDto updateProject(ProjectDto project, String id);

	void addObservation(String id, String observation);

	List<ProjectSeedDto> seedsAsignedToProjects(String projectId);

	List<ProjectDto> findAsignedSeedListToProject(String seedId);

	List<String> projectsNamesList(List<ProjectDto> projectDtoList);

	List<ProjectSeed> findProjectsListBySeedId(String seedId);
}