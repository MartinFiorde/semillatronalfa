package ar.com.semillero.semillatronalfa.controllers;

import ar.com.semillero.semillatronalfa.models.dtos.SeedDto;
import ar.com.semillero.semillatronalfa.models.filters.SeedFilter;
import ar.com.semillero.semillatronalfa.models.project.Project;
import ar.com.semillero.semillatronalfa.models.seed.Seed;
import ar.com.semillero.semillatronalfa.services.ProjectServiceImpl;
import ar.com.semillero.semillatronalfa.services.SeedServiceImpl;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import ar.com.semillero.semillatronalfa.services.interfaces.ProjectService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
// Diferencia controller vs restcontroller: https://medium.com/javarevisited/difference-between-controller-and-restcontroller-in-spring-boot-and-spring-mvc-216578ad445f
@RequestMapping("/seed")
//@PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
public class SeedController {

    private SeedServiceImpl seedService;
    @Autowired
    private ProjectServiceImpl projectService;

    @Autowired
    public SeedController(SeedServiceImpl seedService) {
        this.seedService = seedService;
    }

//    @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
//    @GetMapping("/{id}")
//    @ResponseBody
//    public SeedDto findSeedById(@PathVariable String id) {
//        return seedService.findSeedById(id);
//    }

    @GetMapping("/api/item/{id}")
    @ResponseBody
    public SeedDto findSeedByIdApi(@PathVariable String id) {
        return seedService.findSeedById(id);
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
    @GetMapping("/item/{id}")
    public String findSeedById(ModelMap model, @PathVariable String id) throws ServiceException {
        if (id != null) {
            SeedDto seedDto = seedService.findSeedById(id);
            seedDto.setProjectNames(projectService.projectsNamesList(projectService.findAsignedSeedListToProject(id)));
            model.put("id", id);
            model.put("seedDTO", seedDto);
            model.put("projects", seedService.ListOfProjectActive());
            model.put("hasAttended", seedService.getSeedAttendance(id));
            model.put("invitedTo", seedService.getInvitedTo(id));
        }
        return "pages/semilla.html";
    }


    @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
//  @PreAuthorize("permitAll()")
    @GetMapping("/list")
    public String findSeeds(ModelMap model,
                            @RequestParam(required = false) Optional<String> commissionOrder,
                            @RequestParam(required = false) Optional<String> lastNameOrder) {
        model.addAttribute("commission", seedService.listOfCommision());
        model.addAttribute("projects", seedService.ListOfProjectActive());
        model.addAttribute("countries", seedService.listOfCountry());
        model.addAttribute("seeds", seedService.orderedSeedList(commissionOrder, lastNameOrder));
        model.addAttribute("seedFilter", new SeedFilter());
        return "pages/semillas.html";
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
    @GetMapping("/list/filter")
    public String getProjectsFiltered(Model model, @ModelAttribute SeedFilter seedFilter) {
        model.addAttribute("commission", seedService.listOfCommision());
        model.addAttribute("seeds", seedService.filteredSeed(seedFilter));
        model.addAttribute("projects", seedService.ListOfProjectActive());
        model.addAttribute("countries", seedService.listOfCountry());
        model.addAttribute("seedFilter", seedFilter);
        return "pages/semillas.html";
    }
    /*
    @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
    @GetMapping("/api/filter")
    @ResponseBody
    public List<SeedDto> filteredSeeds(@RequestBody SeedFilter seedFilter, Optional<String> commissionO, Optional<String> lastNameO) {
        return seedService.filteredSeedList(seedFilter, commissionO, lastNameO);
    }*/

    @PostMapping("/api/create")
    @ResponseBody
    public void createSeedApi(@RequestBody Seed seed) {
        seedService.addSeed(seed);
    }

    @PostMapping("/api/create-batch")
    @ResponseBody
    public void createSeedBathApi(@RequestBody List<Seed> seeds) {
        seedService.addSeeds(seeds);
    }

    @PostMapping("/api/update/{id}")
    public void updateSeedApi(ModelMap model, @PathVariable("id") String id, @ModelAttribute SeedDto dto) {
        try {
            seedService.updateSeed(dto, id);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

/*    @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
    @GetMapping("/update/{id}")
    public String updateSeed(ModelMap model, @PathVariable String id) throws ServiceException {
        SeedDto seedDto = seedService.findSeedById(id);
        seedDto.setProjectNames(projectService.projectsNamesList(projectService.findAsignedSeedListToProject(id)));
        model.put("id", id);
        model.put("seedDTO", seedDto);
        model.put("projects", seedService.ListOfProjectActive());
        model.put("hasAttended", seedService.getSeedAttendance(id));
        model.put("invitedTo", seedService.getInvitedTo(id));
    }*/

    @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
    //@PreAuthorize("permitAll()")
    @PostMapping("/update/{id}")
    public String updateSeedPost(ModelMap model, RedirectAttributes attributes, @PathVariable("id") String id, @ModelAttribute SeedDto dto) {
        try {
            seedService.updateSeed(dto, id);
            attributes.addAttribute("msg", "Edición exitosa");
            return "redirect:/seed/item/" + id;
        } catch (Exception ex) {
            attributes.addAttribute("msg", ex.getMessage());
            return "redirect:/seed/update/" + id;
        }
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
    @PostMapping("/api/delete/{id}")
    @ResponseBody
    public ResponseEntity<Object> deleteSeed(@PathVariable("id") String id) {
        if (seedService.findSeedById(id) != null) {
            seedService.deleteSeed(id);
            return new ResponseEntity<>("Removed successfully!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/api/observation/remove/{id}")
    @ResponseBody
    public void deleteObservation(@PathVariable("id") String id) {
        try {
            Seed seed = seedService.findSeed(id);
            seed.getFollowUp().setObservation(null);
            seedService.addSeed(seed);
            //return new ResponseEntity<>("Observation removed successfully!", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();

            //return new ResponseEntity<>("Error", HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/api/asign-project/{id}")
    @ResponseBody
    public void asignProject(@PathVariable("id") String id, @RequestBody List<String> projectName) {
        try {
            Seed seed = seedService.findSeed(id);
            projectName.forEach(name -> {
                Project project = projectService.findProjectByName(name);
                seedService.asignProjectToSeed(seed, project);
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/sheet")
    public ResponseEntity<Resource> downloadSheet() throws IOException {
        Resource resource = new ClassPathResource("static/assets/Plantilla_Semillas_OK.xlsx");
        MediaType mediaType = MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        return ResponseEntity.ok()
                .contentType(mediaType)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + "Plantilla_Semillas_OK.xlsx")
                .body(resource);
    }
}
