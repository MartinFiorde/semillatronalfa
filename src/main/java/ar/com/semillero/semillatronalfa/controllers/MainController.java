package ar.com.semillero.semillatronalfa.controllers;

import ar.com.semillero.semillatronalfa.models.dtos.UserDto;
import ar.com.semillero.semillatronalfa.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
@PreAuthorize("permitAll()") // doc con todas las opciones de autorizacion >>> https://www.baeldung.com/spring-security-expressions
//@PreAuthorize("isAuthenticated()")
//@PreAuthorize("hasAnyRole('ADMIN','ADMIN')")
public class MainController {

    private UserServiceImpl userService;

    @Autowired
    public MainController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    @PreAuthorize("isAuthenticated()")
    public String empty() {
        return "redirect:/index";
    }

    @GetMapping("/index")
    @PreAuthorize("isAuthenticated()")
    public String index() {
        return "index.html";
    }

    @GetMapping("/register")
    @PreAuthorize("permitAll()")
    public String registerUser(ModelMap model) {
        model.put("dto", new UserDto());
        return "pages/register-back.html";
    }

    @PostMapping("/register")
    @PreAuthorize("permitAll()")
    public String registerUserPost(ModelMap model, @ModelAttribute UserDto dto, @RequestParam String passwordConfirm) {
        try {
            model.put("msg", "El usuario se ha registrado correctamente");
            userService.register(dto, passwordConfirm);
            return "redirect:/login";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            //ex.printStackTrace();
            model.put("msg", ex.getMessage());
            model.put("dto", dto);
            return "pages/register-back.html";
        }
    }

    @GetMapping("/login")
    @PreAuthorize("permitAll()")
    public String userLogin(ModelMap model, @PathVariable(required = false) String msg) {
        model.addAttribute("msg", msg);
        return "pages/login.html";
    }
}