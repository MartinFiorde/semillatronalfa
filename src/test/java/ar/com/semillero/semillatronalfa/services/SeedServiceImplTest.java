package ar.com.semillero.semillatronalfa.services;

import ar.com.semillero.semillatronalfa.models.converters.SeedConverter;
import ar.com.semillero.semillatronalfa.models.dtos.SeedDto;
//import ar.com.semillero.semillatronalfa.models.seed.Seed;
//import ar.com.semillero.semillatronalfa.models.seed.SeedContactData;
//import ar.com.semillero.semillatronalfa.models.seed.SeedFollowUp;
//import ar.com.semillero.semillatronalfa.models.seed.SeedPersonalData;
//import ar.com.semillero.semillatronalfa.models.seed.SeedPostulationData;
import ar.com.semillero.semillatronalfa.repositories.SeedAttendanceRepository;
import ar.com.semillero.semillatronalfa.models.seed.*;
import ar.com.semillero.semillatronalfa.repositories.SeedRepository;
import ar.com.semillero.semillatronalfa.repositories.queries.SeedQueries;
import java.time.*;
import javax.persistence.EntityManager;
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SeedServiceImplTest {
    
    // INSTANCES AND CONSTRUCTOR
    @Mock
    private SeedRepository seedRepository;
    @Mock
    private EntityManager entityManager;
    @Mock
    private SeedAttendanceRepository seedAttendanceRepository;
    
    @InjectMocks
    private SeedServiceImpl seedServiceImpl = new SeedServiceImpl(seedRepository, new SeedQueries(), new SeedConverter(seedRepository), entityManager, seedAttendanceRepository);

    private SeedDto seeddto;
    private SeedDto seeddto2;
    
    private Seed seed2;
    private SeedFollowUp followUp2;
    private SeedPersonalData personalData2;
    private SeedContactData contactData2;
    private SeedPostulationData postulationData2;
    
    @BeforeEach
    void setup(){
        seeddto = new SeedDto();
        
        //CONTACT DATA
        seeddto.setId("asd123456");
        seeddto.setEmail("pepe@gmail.com");
        seeddto.setTelephone("656474893");
        seeddto.setLinkedin("milinkedin");
        seeddto.setDiscordUser("@adfilo");
        
        //POSTULATION DATA
        seeddto.setRol("BACKEND");
        seeddto.setTurn("MAÑANA");
        seeddto.setMeetSemilleroBy("REDES SOCIALES");
        seeddto.setStudies("licenciado");
        seeddto.setHobbies("Bailar");
        seeddto.setComment("mmm...");
        
        //PERSONAL DATA
        seeddto.setFirstName("OSCAR");
        seeddto.setLastName("QUIROGA");
        seeddto.setDni(12345678);
        seeddto.setBirthDate(LocalDate.MIN);
        seeddto.setCountry("ARGENTINA");
        seeddto.setCity("MENDOZA");
        seeddto.setGender("MASCULINO");

        //FOLLOW UP
        seeddto.setPostulationDate(LocalDateTime.MIN);
        seeddto.setCommission("E202304");
        seeddto.setRecommendationString("lalalalala");
        seeddto.setRecommendationComment("Es bueno para un futuro");
        seeddto.setCertificationString("recibe certificado");
        seeddto.setObservation("en evaluacion");
        
        //SEED STATUS
        seeddto.setPrimary("por definir");
        seeddto.setSecondary("consiguio trabajo");
        seeddto.setProjectId("qwe09876");
        seeddto.setProjectName("SIGIS");
        seeddto.setDigitalEmployment(true);
        seeddto.setCompanyName("QUINTO");
        
        seed2 = new Seed();
        followUp2 = new SeedFollowUp();
        personalData2 = new SeedPersonalData();
        contactData2 = new SeedContactData();
        postulationData2 = new SeedPostulationData();
        
        seed2.setActive(true);
        
        followUp2.setCertificationString("no dar certificado");
        followUp2.setCommission("E202314");
        followUp2.setObservation("en la cuerda floja");
        followUp2.setPostulationDate(LocalDateTime.parse("2023-04-03T10:15:30"));
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
// TESTS
    
    @Test
    @DisplayName("Should get SeedDto edited and seedId, and persist in Entity with that SeedId the updated information from edited SeedDto")
    public void updateSeedTestMAFMODELO() throws Exception {
        // ARRANGE - Setting up the data that required for the test case
        SeedDto expectedResult = new SeedDto();
        expectedResult = seeddto;
        SeedDto dtoMock = new SeedDto();
        dtoMock = seeddto;
        String idMock = "asd123456";

        // ACT - Calling a Method/Unit that is being tested
        SeedDto result = seedServiceImpl.updateSeed(dtoMock, idMock);

        // ASSERT - Verify that the expected result is correct or not
        assertEquals(result.getCountry(),expectedResult.getCountry());
    }

}
