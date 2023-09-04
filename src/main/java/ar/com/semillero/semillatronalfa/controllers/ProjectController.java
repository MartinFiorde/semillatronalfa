package ar.com.semillero.semillatronalfa.controllers;

import ar.com.semillero.semillatronalfa.models.dtos.ProjectDto;
import ar.com.semillero.semillatronalfa.models.filters.ProjectFilter;
import ar.com.semillero.semillatronalfa.models.converters.ProjectConverter;
import ar.com.semillero.semillatronalfa.models.project.Project;
import java.util.List;

import ar.com.semillero.semillatronalfa.services.ProjectServiceImpl;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.ModelMap;

@Controller
@PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    ProjectServiceImpl projectService;

    @Autowired
    ProjectConverter projectConverter;

    @GetMapping("/list")
    public String getProjects(ModelMap model) {
            model.addAttribute("projects", projectService.findProjectList());
            model.addAttribute("projectFilters", new ProjectFilter());
            model.addAttribute("commissionOptions", projectService.listOfProjectCommissions());
            return "pages/proyectos.html";
    }
    
    @GetMapping("/list/filter")
    public String getProjectsFiltered(Model model, @ModelAttribute ProjectFilter projectFilter) {
            model.addAttribute("projects", projectService.filteredProjects(projectFilter));
            model.addAttribute("projectFilters", projectFilter);
            model.addAttribute("commissionOptions", projectService.listOfProjectCommissions());
            return "pages/proyectos.html";
    }

    @GetMapping("/{id}")
    public String getProject(Model model, @PathVariable("id") String projectId) {
            model.addAttribute("project", projectService.findProjectByIdDto(projectId));
            return "pages/proyecto.html";
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/filter")
    @ResponseBody
    List<ProjectDto> getFilteredProjects(@RequestBody ProjectFilter projectFilter) {
            return projectService.filteredProjects(projectFilter);
    }
    
    @PreAuthorize("permitAll()")
    @GetMapping("/filterMAF")
    @ResponseBody
    List<ProjectDto> getFilteredProjectsMAFTest() {
        //ProjectFilter projectFilter = new ProjectFilter("proyecto");
        ProjectFilter projectFilter = new ProjectFilter("proyecto", new String[]{"Landing Page básica"}, new String[]{"Descartado","Cerrado"}, null, null, null, LocalDate.parse("2023-08-01"), LocalDate.parse("2023-11-01"), new String[]{"F23"}, null, null);
            return projectService.filteredProjects(projectFilter);
    }
    

    @PreAuthorize("permitAll()")
    @PostMapping("/create")
    public String addProject(@ModelAttribute ProjectDto dto, Model model) {
            Project project = projectConverter.dtoToEntity(dto);
            projectService.addProject(project);
            model.addAttribute("project", project);
            return "redirect:/project/" + project.getId();
    }
    
    // COMENTARIO MAF: confirmar si funciona teniendo un usuario autenticado, o si hace falta agregar la anotacion @PreAuthorize("permitAll()")
    @PreAuthorize("permitAll()")
    @PostMapping("/import")
    @ResponseBody
    public void addProject(@RequestBody List<ProjectDto> projectDtoList) {
            projectService.importProjects(projectDtoList);
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/add-observation/{id}")
    @ResponseBody
    public void addObservation(@PathVariable("id") String projectId, @RequestParam("observation") String observation) {
            projectService.addObservation(projectId, observation);
    }

    @PostMapping("/update/{id}")
    public String updateProject(@PathVariable("id") String projectId, @ModelAttribute ProjectDto dto, Model model) {
            dto.setId(projectId);
            model.addAttribute("project", projectService.updateProject(dto));
            return "redirect:/project/" + projectId;
    }

    // COMENTARIO MAF: Juli revisa este método, no esta guardando el cambio de estado en la base de datos
    @PatchMapping("/delete/{id}")
    public void deleteProject(@PathVariable String projectId) {
            Project project = projectService.findProjectById(projectId);
            if(project != null) {
                    project.setActive(false);
            }
    }
}