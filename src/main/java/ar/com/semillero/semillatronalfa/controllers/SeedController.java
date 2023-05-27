package ar.com.semillero.semillatronalfa.controllers;

<<<<<<< HEAD

=======
>>>>>>> e6b3efb184945d6e78a3ad97bed9de7c3c451500
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

<<<<<<< HEAD
@Controller
@RequestMapping("/seed")
public class SeedController {

=======
@Controller // Diferencia controller vs restcontroller: https://medium.com/javarevisited/difference-between-controller-and-restcontroller-in-spring-boot-and-spring-mvc-216578ad445f
@RequestMapping("/seed")
public class SeedController {
    
>>>>>>> e6b3efb184945d6e78a3ad97bed9de7c3c451500
    private SeedService seedService;

    @Autowired
    public SeedController(SeedService seedService) {
        this.seedService = seedService;
    }
<<<<<<< HEAD

=======
    
>>>>>>> e6b3efb184945d6e78a3ad97bed9de7c3c451500
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
<<<<<<< HEAD
=======
        System.out.println(seed);
>>>>>>> e6b3efb184945d6e78a3ad97bed9de7c3c451500
        seedService.addSeed(seed);
    }
}

