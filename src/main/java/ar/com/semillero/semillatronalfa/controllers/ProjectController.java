package ar.com.semillero.semillatronalfa.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.semillero.semillatronalfa.entities.proyect.Project;
import ar.com.semillero.semillatronalfa.services.project.ProjectService;

@Controller
@RequestMapping("/project")
public class ProjectController {
	@Autowired
	ProjectService projectService;

	@GetMapping("/list")
	@ResponseBody
	List<Project> getProjects() {
		return projectService.findProjects();
	}

	@PostMapping("/create")
	@ResponseBody
	void addProject(@RequestBody Project project) {
		projectService.addProject(project);
	}
}
