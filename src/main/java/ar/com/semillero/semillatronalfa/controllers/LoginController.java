package ar.com.semillero.semillatronalfa.controllers;

import ar.com.semillero.semillatronalfa.entities.user.User;
import ar.com.semillero.semillatronalfa.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    UserService userService;

    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<Object> authenticate(@RequestBody User user) {
        if(userService.matchUser(user.getUsername(), user.getPassword()) == null) {
            return new ResponseEntity<>("Access denied!", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>("Access granted!", HttpStatus.OK);
    }


}
