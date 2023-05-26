package ar.com.semillero.semillatronalfa.controllers;

import ar.com.semillero.semillatronalfa.services.commission.CommissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/seed")
public class CommmissionController {

    private CommissionService commissionService;

    @Autowired
    public CommmissionController(CommissionService commissionService) {
        this.commissionService = commissionService;
    }

}
