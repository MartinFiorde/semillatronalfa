package ar.com.semillero.semillatronalfa.services.project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.semillero.semillatronalfa.entities.proyect.Project;
import ar.com.semillero.semillatronalfa.repositories.Proyect.ProjectRepository;

@Service
public class ProjectImplementation implements ProjectService {

	@Autowired
	ProjectRepository proyectRepository;

	@Override
	public List<Project> findProjects() {
		return proyectRepository.findAll();
	}

	@Override
	public List<Project> findProjectList() {
		return proyectRepository.getProjectList();
	}

	@Override
	public Project findProjectById(String id) {
		return proyectRepository.findById(id).orElse(null);
	}

	@Override
	public void addProject(Project proyect) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteProject(String id) {
		// TODO Auto-generated method stub

	}

}