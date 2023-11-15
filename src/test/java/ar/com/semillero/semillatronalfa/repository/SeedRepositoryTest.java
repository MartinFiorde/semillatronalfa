
package ar.com.semillero.semillatronalfa.repository;

import ar.com.semillero.semillatronalfa.models.seed.Seed;
import ar.com.semillero.semillatronalfa.models.seed.SeedContactData;
import ar.com.semillero.semillatronalfa.models.seed.SeedFollowUp;
import ar.com.semillero.semillatronalfa.models.seed.SeedPersonalData;
import ar.com.semillero.semillatronalfa.models.seed.SeedPostulationData;
import ar.com.semillero.semillatronalfa.repositories.SeedRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.Assert;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SeedRepositoryTest {
    
    @Autowired
    private SeedRepository seedRepository;
    
    private Seed seed;
    private SeedFollowUp followUp;
    private SeedPersonalData personalData;
    private SeedContactData contactData;
    private SeedPostulationData postulationData;
    
    private Seed seed2;
    private SeedFollowUp followUp2;
    private SeedPersonalData personalData2;
    private SeedContactData contactData2;
    private SeedPostulationData postulationData2;
    
    @BeforeEach
    void setup(){
        seed = new Seed();
        followUp = new SeedFollowUp();
        personalData = new SeedPersonalData();
        contactData = new SeedContactData();
        postulationData = new SeedPostulationData();
        
        seed.setActive(true);
        
        followUp.setCertificationString("recibe certificado");
        followUp.setCommission("E202304");
        followUp.setObservation("en evaluacion");
        followUp.setPostulationDate(LocalDateTime.parse("2023-02-03T10:15:30"));
        followUp.setRecommendationComment("Es bueno para un futuro");
        seed.setFollowUp(followUp);
        
        personalData.setBirthDate(LocalDate.MIN);
        personalData.setCity("Mendoza");
        personalData.setCountry("Argentina");
        personalData.setDni(12345678);
        personalData.setFirstName("OSCAR");
        personalData.setGender("Masculino");
        personalData.setLastName("QUIROGA");
        seed.setPersonalData(personalData);
        
        contactData.setDiscordUser("@adfilo");
        contactData.setEmail("pepe@gmail.com");
        contactData.setLinkedin("milinkedin");
        contactData.setTelephone("656474893");
        seed.setPersonalData(personalData);
        
        postulationData.setHobbies("Bailar");
        postulationData.setRol("BACKEND");
        postulationData.setMeetSemilleroBy("REDES SOCIALES");
        postulationData.setStudies("licenciado");
        postulationData.setTurn("MAÑANA");
        seed.setPostulationData(postulationData);
        
        
        seed2 = new Seed();
        followUp2 = new SeedFollowUp();
        personalData2 = new SeedPersonalData();
        contactData2 = new SeedContactData();
        postulationData2 = new SeedPostulationData();
        
        seed2.setActive(true);
        
        followUp2.setCertificationString("no dar certificado");
        followUp2.setCommission("E202314");
        followUp2.setObservation("en la cuerda floja");
        followUp2.setPostulationDate(LocalDateTime.parse("2022-08-03T10:15:30"));
        followUp2.setRecommendationComment("Es buen cocinero");
        seed2.setFollowUp(followUp2);
        
        personalData2.setBirthDate(LocalDate.MIN);
        personalData2.setCity("Rio de Janeiro");
        personalData2.setCountry("Brasil");
        personalData2.setDni(23456789);
        personalData2.setFirstName("FLAVIA");
        personalData2.setGender("Femenino");
        personalData2.setLastName("FERNANDEZ");
        seed2.setPersonalData(personalData2);
        
        contactData2.setDiscordUser("@tilo");
        contactData2.setEmail("july@gmail.com");
        contactData2.setLinkedin("tulinkedin");
        contactData2.setTelephone("956474893");
        seed2.setPersonalData(personalData2);
        
        postulationData2.setHobbies("Correr");
        postulationData2.setRol("UX");
        postulationData2.setMeetSemilleroBy("REDES SOCIALES");
        postulationData2.setStudies("Contadora");
        postulationData2.setTurn("TARDE");
        seed2.setPostulationData(postulationData2);
    }
    
    //TEST PARA VERIFICAR SI SE GUARDA EL OBJETO Y ALGUNOS ELEMENTOS ESPECIFICOS
    @Test
    @DisplayName("Save Seed test")
    void saveSeedTest(){
        Seed newSeed = seedRepository.save(seed);
        
        assertNotNull(newSeed);
        assertEquals("E202304", newSeed.getFollowUp().getCommission());
        assertEquals("pepe@gmail.com",newSeed.getContactData().getEmail());
        assertEquals("Masculino", newSeed.getPersonalData().getGender());
        assertEquals("BACKEND", newSeed.getPostulationData().getRol());
    }
    //TEST PARA VERIFICAR QUE SE GUARDE EN UNA LISTA
    @Test
    @DisplayName("Fetch seed list test")        
    void getSeedListTest(){
        Seed newSeed = seedRepository.save(seed);
        Seed newSeed2 = seedRepository.save(seed2);
        
        List<Seed> seedList = seedRepository.findAll();
        
        assertNotNull(seedList);
        assertEquals(2,seedList.size());
    }
    //TEST PARA VERIFICAR QUE FUNCIONE LA BUSQUEDA POR ID DE SEMILLA
    @Test
    @DisplayName("search seed by ID test")
    void getEventByIdTest(){
        Seed newSeed = seedRepository.save(seed);
        
        Seed seedTest = seedRepository.findSeedById(newSeed.getId());
        
        assertNotNull(seedTest);
        assertEquals("Bailar", seedTest.getPostulationData().getHobbies());
    }
    @Test
    @DisplayName("uodate seed test")
    void updateSeedTest(){
        Seed newSeed = seedRepository.save(seed);
        contactData2 = new SeedContactData();
        
        Seed changeSeed = seedRepository.findSeedById(newSeed.getId());
        contactData2.setTelephone("999999999");
        changeSeed.setContactData(contactData2);
        
        assertNotNull(changeSeed);
        assertEquals("999999999",changeSeed.getContactData().getTelephone());
        
    }
}
