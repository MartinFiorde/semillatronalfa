package ar.com.semillero.semillatronalfa.services;

import ar.com.semillero.semillatronalfa.models.dtos.ProjectDto;
import ar.com.semillero.semillatronalfa.models.filters.ProjectFilter;
import ar.com.semillero.semillatronalfa.models.converters.ProjectConverter;
import java.util.List;

import ar.com.semillero.semillatronalfa.repositories.queries.ProjectQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import ar.com.semillero.semillatronalfa.models.project.Project;
import ar.com.semillero.semillatronalfa.repositories.ProjectRepository;
import ar.com.semillero.semillatronalfa.services.interfaces.ProjectService;
import java.util.Collections;
import java.util.HashSet;
import java.util.stream.Collectors;
import org.springframework.web.client.HttpClientErrorException;

import javax.persistence.*;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    ProjectConverter projectConverter;

    @Override
    public List<Project> findProjects() {
        return projectRepository.findAll();
    }

    @Override
    public List<ProjectDto> findProjectList() {
        List<Project> projectList = entityManager.createNativeQuery(ProjectQueries.findProjectList(), Project.class).getResultList();
        return projectConverter.entitiesToDto(projectList);
        //return projectList.stream().map(ProjectDto::new).collect(Collectors.toList());
    }

    @Override
    public List<ProjectDto> filteredProjects(ProjectFilter projectFilter) {
        List<Project> projects = entityManager.createNativeQuery(ProjectQueries.projectFilter(projectFilter), Project.class)
                .getResultList();
        return projectConverter.entitiesToDto(projects);
        //return projects.stream().map(ProjectDto::new).collect(Collectors.toList());
    }

    @Override
    public Project findProjectById(String id) {
        return projectRepository.findById(id).orElse(null);
    }

    @Override
    public ProjectDto findProjectByIdDto(String id) {
        return projectConverter.entityToDto(projectRepository.findById(id).orElse(null));
        //return new ProjectDto(projectRepository.findById(id).orElse(null));
    }


    /*@Override
	public ProjectDto addProject(ProjectDto dto) {
            Project project = projectConverter.dtoToEntity(dto);
		//project.getDetails().setProject(project);
		//project.getManagementTeam().setProject(project);
		projectRepository.save(project);
                return projectConverter.entityToDto(project); // COMENTARIO MARTIN. CONFIRMAR QUE DEVIELVE DTO CON ID, 99% seguro que si, pero me queda la duda...
	}*/
    @Override
    public void addProject(Project project) {
        projectRepository.save(project);
    }

    @Override
    public void importProjects(List<ProjectDto> projectDtoList) {
        projectDtoList.stream().forEach(project -> {
            projectRepository.save(projectConverter.dtoToEntity(project));
        });
    }

    @Override
    public void deleteProject(String id) {

    }

    public ProjectDto updateProject(ProjectDto dto) {
        Project project = projectConverter.dtoToEntity(dto);
        projectRepository.save(project);
        return projectConverter.entityToDto(project);
    }

    @Override
    public void addObservation(String id, String observation) {
        try {
            if (projectRepository.findById(id).orElse(null) != null) {
                Project project = projectRepository.findById(id).orElse(null);
                project.getDetails().setObservations(observation);
                projectRepository.save(project);
            } else {
                throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public List<String> listOfProjectCommissions() {
        return projectRepository.listOfProjectCommissions();
    }
}
