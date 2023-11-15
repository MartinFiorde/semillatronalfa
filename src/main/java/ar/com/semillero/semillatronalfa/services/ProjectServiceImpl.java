package ar.com.semillero.semillatronalfa.services;

import ar.com.semillero.semillatronalfa.models.converters.SeedConverter;
import ar.com.semillero.semillatronalfa.models.dtos.ProjectDto;
import ar.com.semillero.semillatronalfa.models.dtos.ProjectSeedDto;
import ar.com.semillero.semillatronalfa.models.dtos.SeedDto;
import ar.com.semillero.semillatronalfa.models.filters.ProjectFilter;
import ar.com.semillero.semillatronalfa.models.converters.ProjectConverter;

import java.util.ArrayList;
import java.util.List;

import ar.com.semillero.semillatronalfa.models.project.ProjectSeed;
import ar.com.semillero.semillatronalfa.models.seed.Seed;
import ar.com.semillero.semillatronalfa.repositories.ProjectSeedRepository;
import ar.com.semillero.semillatronalfa.repositories.SeedRepository;
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

    @Autowired
    SeedRepository seedRepository;

    @Autowired
    SeedConverter seedConverter;

    @Autowired
    ProjectSeedRepository projectSeedRepository;

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
        return projectRepository.findProjectById(id);
    }

    @Override
    public Project findProjectByName(String projectName) {
        Project project = null;
        try {
            project = projectRepository.findProjectByName(projectName);
        } catch(Exception e) {
            e.printStackTrace(System.out);
        }
        return project;
    }

    public Boolean doesProjectNameExist(String projectName) {
        Project project = projectRepository.findProjectByName(projectName);
        return project != null;
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

    public ProjectDto updateProject(ProjectDto dto, String id) {
        Project project = projectConverter.dtoToEntity(dto);
        project.setId(id);
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

    /*@Override
    public List<SeedDto> seedsAsignedToProjects(String projectId) {
        List<SeedDto> seedDtoList = null;
        try {
            List<Seed> seedList = seedRepository.findAsignedSeedsToProject(projectId);
            seedDtoList = seedConverter.entitiesToDto(seedList);
        } catch(Exception e) {
            e.printStackTrace(System.out);
        }
        return seedDtoList;
    }*/
    @Override
    public List<ProjectSeedDto> seedsAsignedToProjects(String projectId) {
        List<ProjectSeedDto> projectSeedDtoList = new ArrayList<>();
        try {
            List<ProjectSeed> projectSeedList = projectSeedRepository.findAsignedSeedsToProject(projectId);
            for (ProjectSeed projectSeed : projectSeedList) {
                ProjectSeedDto projectSeedDto = new ProjectSeedDto();
                projectSeedDto.setId(projectSeed.getId());
                projectSeedDto.setProject(projectSeed.getProject());
                projectSeedDto.setSeed(projectSeed.getSeed());
                projectSeedDtoList.add(projectSeedDto);
            }
        } catch(Exception e) {
            e.printStackTrace(System.out);
        }
        return projectSeedDtoList;
    }

    @Override
    public List<ProjectDto> findAsignedSeedListToProject(String seedId) {
        List<Project> projects = projectRepository.findAsignedSeedListToProject(seedId);
        for (Project project : projects) {
            System.out.println(project.getProjectName());
        }
        List<ProjectDto> projectDto = projectConverter.entitiesToDto(projects);
        return projectDto;
    }

    @Override
    public List<String> projectsNamesList(List<ProjectDto> projectDtoList) {
        List<String> namesList = new ArrayList<>();
        for (ProjectDto projectDto : projectDtoList) {
            namesList.add(projectDto.getProjectName());
        }
        return namesList;
    }

    @Override
    public List<ProjectSeed> findProjectsListBySeedId(String seedId) {
        return projectSeedRepository.findProjectsListBySeedId(seedId);
    }

    public List<String> listOfProjectCommissions() {
        return projectRepository.listOfProjectCommissions();
    }
}
