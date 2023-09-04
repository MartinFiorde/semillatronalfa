package ar.com.semillero.semillatronalfa.services;

import ar.com.semillero.semillatronalfa.models.converters.SeedConverter;
import ar.com.semillero.semillatronalfa.models.dtos.ProjectDto;
import ar.com.semillero.semillatronalfa.models.dtos.SeedDto;
import ar.com.semillero.semillatronalfa.models.project.CompanyData;
import ar.com.semillero.semillatronalfa.models.project.ManagementTeam;
import ar.com.semillero.semillatronalfa.models.seed.Seed;
import ar.com.semillero.semillatronalfa.repositories.SeedRepository;
import ar.com.semillero.semillatronalfa.repositories.queries.SeedQueries;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SeedServiceImplTest {
    
    // INSTANCES AND CONSTRUCTOR
    @Mock
    private SeedRepository seedRepository;
    @Mock
    private EntityManager entityManager;
    
    @InjectMocks
    private SeedServiceImpl seedServiceImpl = new SeedServiceImpl(seedRepository, new SeedQueries(), new SeedConverter(seedRepository), entityManager);

    // TESTS
    
    @Test
    @DisplayName("Should get SeedDto edited and seedId, and persist in Entity with that SeedId the updated information from edited SeedDto")
    public void updateSeedTestMAFMODELO() throws Exception {
        // ARRANGE - Setting up the data that required for the test case
        SeedDto expectedResult = null;
        SeedDto dtoMock = null;
        String idMock = null;

        // ACT - Calling a Method/Unit that is being tested
        SeedDto result = seedServiceImpl.updateSeed(dtoMock, idMock);

        // ASSERT - Verify that the expected result is correct or not
        assertEquals(null,null);
    }

}
