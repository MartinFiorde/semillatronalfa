package ar.com.semillero.semillatronalfa.services.project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.semillero.semillatronalfa.entities.project.Project;
import ar.com.semillero.semillatronalfa.repositories.project.ProjectRepository;

@Service
public class ProjectImplementation implements ProjectService {

	@Autowired
	ProjectRepository projectRepository;
	

	@Override
	public List<Project> findProjects() {
		return projectRepository.findAll();
	}

	@Override
	public List<Project> findProjectList() {
		return projectRepository.getProjectList();
	}

	@Override
	public Project findProjectById(String id) {
		return projectRepository.findById(id).orElse(null);
	}

	@Override
	public void addProject(Project project) {
		project.getProjectData().setProject(project);
		projectRepository.save(project);
	}

	@Override
	public void deleteProject(String id) {

	}

}