package ar.com.semillero.semillatronalfa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;


@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class}) // (exclude = {UserDetailsServiceAutoConfiguration.class}) >> quita el mensaje de auto generated password
public class SemillatronApplication {
    public static void main(String[] args) {
        SpringApplication.run(SemillatronApplication.class, args);
    }

    /*@Bean
    public CommandLineRunner initData(AllyRepository allyRepository, AllyContactDataRepository contactDataRepository, ProjectRepository projectRepository){
        return (args) -> {

            AllyContactData contactData = new AllyContactData("email@gmail.com", "+12345735", "Buenos Aires 231");
            Ally ally = new Ally("COMPANY", "B", new Date(), "Cuidado ambiental", "Persona", contactData);
            contactData.setAlly(ally);
            allyRepository.save(ally);

            ProjectData projectData = new ProjectData("Título", new Date(), new Date(), new Date(), "Propósito", "Ods", "Status");
            Project project = new Project(projectData, ally);
            projectData.setProject(project);
            projectRepository.save(project);


        };
    }*/
}
