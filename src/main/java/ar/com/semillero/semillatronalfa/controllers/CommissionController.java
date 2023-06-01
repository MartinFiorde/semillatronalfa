package ar.com.semillero.semillatronalfa.controllers;

import ar.com.semillero.semillatronalfa.entities.Commission;
import ar.com.semillero.semillatronalfa.services.commission.CommissionService;
import ar.com.semillero.semillatronalfa.services.commission.CommissionService;
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
@RequestMapping("/commission")
public class CommissionController {
    
    private CommissionService commissionService;

    @Autowired
    public CommissionController(CommissionService commissionService) {
        this.commissionService = commissionService;
    }
    
    @GetMapping("/{id}")
    @ResponseBody
    public Commission findEventById(@PathVariable String id) {
        return commissionService.findCommissionById(id);
    }

    @GetMapping("/list")
    @ResponseBody
    public List<Commission> findEvents() {
        return commissionService.getAll();
    }

    @PostMapping("/create")
    @ResponseBody
    public void createCommission(@RequestBody Commission commission) {
        System.out.println(commission);
        commissionService.save(commission);
    }
}

