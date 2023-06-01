package ar.com.semillero.semillatronalfa.controllers;

import ar.com.semillero.semillatronalfa.entities.seed.Seed;
import ar.com.semillero.semillatronalfa.services.seed.SeedService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping("/list")
    @ResponseBody
    public List<Seed> findEvents() {
        return seedService.findAllSeeds();
    }

    @PostMapping("/create")
    @ResponseBody
    public void createSeed(@RequestBody Seed seed) {
        seedService.addSeed(seed);
    }
    
    @PostMapping("/create-batch")
    @ResponseBody
    public void createSeed(@RequestBody List<Seed> seeds) {
        seedService.addSeeds(seeds);
    }
}

