package ar.com.semillero.semillatronalfa.controllers;

import ar.com.semillero.semillatronalfa.entities.seed.Seed;
import ar.com.semillero.semillatronalfa.services.seed.SeedService;
import java.util.List;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller // Diferencia controller vs restcontroller: https://medium.com/javarevisited/difference-between-controller-and-restcontroller-in-spring-boot-and-spring-mvc-216578ad445f
@RequestMapping("/seed")
public class SeedController {

    private SeedService seedService;

    @Autowired
    public SeedController(SeedService seedService) {
        this.seedService = seedService;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Seed findEventById(@PathVariable String id) {
        return seedService.findSeedById(id);
    }
    
    @GetMapping("/api/item/{id}")
    @ResponseBody
    public Seed findSeedById(@PathVariable String id) {
        return seedService.findSeedById(id);
    }

    @GetMapping("/item/{id}")
    public String mostrarLibro(ModelMap model, @PathVariable String id) throws ServiceException {
        if (id != null) {
            model.put("id", id);
            model.put("seed", seedService.findSeedById(id));
        }
        return "pages/semilla.html";
    }
    
    @GetMapping("/api/list")
    @ResponseBody
    public List<Seed> findAllSeeds() {
        return seedService.findAllSeeds();
    }

    @GetMapping("/list")
    public String findSeedHtml(ModelMap model) {
        model.addAttribute("seeds", seedService.findAllSeeds());
        return "pages/semillas.html";
    }
    
    @PostMapping("/api/create")
    @ResponseBody
    public void createSeed(@RequestBody Seed seed) {
        seedService.addSeed(seed);
    }
    
    @PostMapping("/api/create-batch")
    @ResponseBody
    public void createSeed(@RequestBody List<Seed> seeds) {
        seedService.addSeeds(seeds);
    }
}

