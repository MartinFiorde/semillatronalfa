/*
package ar.com.semillero.semillatronalfa.utils.deprecated;

import ar.com.semillero.semillatronalfa.models.User;
import ar.com.semillero.semillatronalfa.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Deprecated
@Controller
//@RequestMapping("/login")
public class LoginController {

    @Autowired
    UserService userService;
    //agregar aca la anterior logica de Juli
    
    @PostMapping("/")
    public String authenticate(@RequestBody User user) {
        return (userService.matchUser(user.getUsername(), user.getPassword()) == null) ?
             "pages/login.html" : "index.html";
    }

    @GetMapping
    //@ResponseBody
    public String authenticateUser() {
        return "pages/login.html";
    }
}
*/