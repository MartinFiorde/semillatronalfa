package ar.com.semillero.semillatronalfa.models.converters;

import ar.com.semillero.semillatronalfa.models.dtos.ProjectDto;
import ar.com.semillero.semillatronalfa.models.dtos.SeedDto;
import ar.com.semillero.semillatronalfa.models.project.CompanyData;
import ar.com.semillero.semillatronalfa.models.project.ManagementTeam;
import ar.com.semillero.semillatronalfa.models.project.Project;
import ar.com.semillero.semillatronalfa.models.project.ProjectDetails;
import ar.com.semillero.semillatronalfa.models.seed.Seed;
import ar.com.semillero.semillatronalfa.repositories.ProjectRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ProjectConverterTest {

    // INSTANCES AND CONSTRUCTOR
    @Mock
    private ProjectRepository projectRepository;
    @InjectMocks
    private ProjectConverter projectConverter = new ProjectConverter(projectRepository);

    // TESTS
    /*@Test
    @DisplayName("Should turn Dto into a valid Entity")
    public void dtoToEntityTest() {
        // ARRANGE - Setting up the data that required for the test case
        Project expectedResult = new Project(null, true, "Semillatron", LocalDateTime.parse("2023-04-03T10:15:30"), "E23", "No", "Mendoza", "1,3,5", null, null, null, new ArrayList<Seed>());
        // MAF comment: id esperado es igual a null porque el setter esta bloqueado en la entidad
        expectedResult.setDetails(new ProjectDetails(null, "Empleabilidad", "Sistema de Administración de cursos y formación (talleres, charlas, asistencias, etc)", "Activo", "Relevamiento y reuniones iniciales", null, LocalDate.parse("2023-04-01"), LocalDate.parse("2023-09-15"), "4,5 meses", null));
        expectedResult.setManagementTeam(new ManagementTeam(null, "Nico", "nico@gmail.com", "1234", "nicoDiscord", null, null, null, null, null, null, null, null, null));
        expectedResult.setCompanyData(new CompanyData(null, "Semillero", "Seba", "Mendoza", "1234", "semillero@gmail.com", null));
        ProjectDto mock = new ProjectDto("id1", "Semillatron", LocalDateTime.parse("2023-04-03T10:15:30"), "E23", "No", "Mendoza", "1,3,5",
                "Empleabilidad", "Sistema de Administración de cursos y formación (talleres, charlas, asistencias, etc)", "Activo", "Relevamiento y reuniones iniciales", null, LocalDate.parse("2023-04-01"), LocalDate.parse("2023-09-15"), "4,5 meses",
                "Nico", "nico@gmail.com", "1234", "nicoDiscord", null, null, null, null, null, null, null, null,
                "Semillero", "Seba", "Mendoza", "1234", "semillero@gmail.com", new ArrayList<SeedDto>());

        // ACT - Calling a Method/Unit that is being tested
        Project result = projectConverter.dtoToEntity(mock);

        // ASSERT - Verify that the expected result is correct or not
        assertEquals(expectedResult.getId(), result.getId());
        assertEquals(expectedResult.getProjectName(), result.getProjectName());
        assertNotNull(result);
    }*/

    /*@Test
    @DisplayName("Should turn Entity into a valid Dto")
    public void entityToDtoTest() {
        // ARRANGE - Setting up the data that required for the test case
        ProjectDto expectedResult = new ProjectDto("id1", "Semillatron", LocalDateTime.parse("2023-04-03T10:15:30"), "E23", "No", "Mendoza", "1,3,5",
                "Empleabilidad", "Sistema de Administración de cursos y formación (talleres, charlas, asistencias, etc)", "Activo", "Relevamiento y reuniones iniciales", null, LocalDate.parse("2023-04-01"), LocalDate.parse("2023-09-15"), "4,5 meses",
                "Nico", "nico@gmail.com", "1234", "nicoDiscord", null, null, null, null, null, null, null, null,
                "Semillero", "Seba", "Mendoza", "1234", "semillero@gmail.com", new ArrayList<SeedDto>());
        Project mock = new Project("id1", true, "Semillatron", LocalDateTime.parse("2023-04-03T10:15:30"), "E23", "No", "Mendoza", "1,3,5", null, null, null, new ArrayList<Seed>());
        mock.setDetails(new ProjectDetails("pd1", "Empleabilidad", "Sistema de Administración de cursos y formación (talleres, charlas, asistencias, etc)", "Activo", "Relevamiento y reuniones iniciales", null, LocalDate.parse("2023-04-01"), LocalDate.parse("2023-09-15"), "4,5 meses", null));
        mock.setManagementTeam(new ManagementTeam("mt1", "Nico", "nico@gmail.com", "1234", "nicoDiscord", null, null, null, null, null, null, null, null, null));
        mock.setCompanyData(new CompanyData("cd1", "Semillero", "Seba", "Mendoza", "1234", "semillero@gmail.com", null));

        // ACT - Calling a Method/Unit that is being tested
        ProjectDto result = projectConverter.entityToDto(mock);

        // ASSERT - Verify that the expected result is correct or not
        assertEquals(expectedResult.getId(), result.getId());
        assertEquals(expectedResult.getProjectName(), result.getProjectName());
        assertNotNull(result);
    }*/
}
