package ar.com.semillero.semillatronalfa;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

// ESTE METODO PERMITE USAR TODA LA APLICACIÓN SIN CONTROLES DE SEGURIDAD - REF: https://stackoverflow.com/questions/36280181/disabling-spring-security-in-spring-boot-app
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests().antMatchers("/").permitAll();
    }

}
