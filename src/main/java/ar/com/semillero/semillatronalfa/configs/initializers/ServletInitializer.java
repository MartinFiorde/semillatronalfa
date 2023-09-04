package ar.com.semillero.semillatronalfa.configs.initializers;

import ar.com.semillero.semillatronalfa.SemillatronApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SemillatronApplication.class);
	}

}
