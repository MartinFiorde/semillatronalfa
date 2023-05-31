package ar.com.semillero.semillatronalfa.controllers;

import ar.com.semillero.semillatronalfa.dtos.allies.AllyDto;
import ar.com.semillero.semillatronalfa.entities.ally.Ally;
import ar.com.semillero.semillatronalfa.services.ally.AllyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/allies")
public class AllyController {
    @Autowired
    AllyService allyService;

    @GetMapping("/list")
    @ResponseBody
    List<Ally> getAllies() {
        return allyService.findAllies();
    }

    @GetMapping("/ally/{id}")
    @ResponseBody
    AllyDto getAlly(@PathVariable String id) {
        return allyService.findAllyById(id);
    }

    @PostMapping("/create")
    @ResponseBody
    void addAlly(@RequestBody Ally ally) {
        allyService.addAlly(ally);
    }
}
