package ar.com.semillero.semillatronalfa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class}) // (exclude = {UserDetailsServiceAutoConfiguration.class}) >> quita el mensaje de auto generated password
public class SemillatronApplication {
    public static void main(String[] args) {SpringApplication.run(SemillatronApplication.class, args);}
}
