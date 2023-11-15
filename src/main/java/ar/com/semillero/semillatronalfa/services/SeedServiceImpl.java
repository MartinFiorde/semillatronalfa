package ar.com.semillero.semillatronalfa.services;

import ar.com.semillero.semillatronalfa.models.converters.SeedConverter;
import ar.com.semillero.semillatronalfa.models.dtos.ProjectDto;
import ar.com.semillero.semillatronalfa.models.project.Project;
import ar.com.semillero.semillatronalfa.models.project.ProjectSeed;
import ar.com.semillero.semillatronalfa.repositories.ProjectSeedRepository;
import ar.com.semillero.semillatronalfa.repositories.SeedAttendanceRepository;
import ar.com.semillero.semillatronalfa.services.interfaces.ProjectService;
import ar.com.semillero.semillatronalfa.services.interfaces.SeedService;
import ar.com.semillero.semillatronalfa.models.dtos.SeedDto;
import ar.com.semillero.semillatronalfa.models.filters.SeedFilter;
import ar.com.semillero.semillatronalfa.models.seed.Seed;
import ar.com.semillero.semillatronalfa.repositories.queries.SeedQueries;
import ar.com.semillero.semillatronalfa.repositories.SeedRepository;
import ar.com.semillero.semillatronalfa.utils.NullUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class SeedServiceImpl implements SeedService {

    private SeedRepository seedRepository;
    private SeedQueries seedQueries;
    private SeedConverter seedConverter;
    private SeedAttendanceRepository seedAttendanceRepository;
    @Autowired
    private ProjectSeedRepository projectSeedRepository;
    @Autowired
    private ProjectService projectService;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public SeedServiceImpl(SeedRepository seedRepository, @Qualifier("seed-queries") SeedQueries seedQueries,
                           SeedConverter seedConverter, EntityManager entityManager, SeedAttendanceRepository seedAttendanceRepository) {
        this.seedRepository = seedRepository;
        this.seedQueries = seedQueries;
        this.seedConverter = seedConverter;
        this.entityManager = entityManager;
        this.seedAttendanceRepository = seedAttendanceRepository;
    }

    @Override
    public List<Seed> findAllSeeds() {
        return seedRepository.findAll();
    }

    @Override
    public List<SeedDto> orderedSeedList(Optional<String> commissionOrder, Optional<String> lastNameOrder) {
        return seedConverter.entitiesToDto(entityManager.createNativeQuery(seedQueries.orderedSeedList(commissionOrder, lastNameOrder), Seed.class).getResultList());
    }

    @Override
    public List<SeedDto> filteredSeedList(SeedFilter seedFilter, Optional<String> commissionO, Optional<String> lastNameO) {
        List<Seed> seedList = entityManager.createNativeQuery(
                seedFilter.getSearchBar() != null ? SeedQueries.seachBarSeed(seedFilter, commissionO, lastNameO)
                : SeedQueries.filterSeed(seedFilter), Seed.class).getResultList();
        return seedConverter.entitiesToDto(seedList);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void addSeed(Seed seed) {
        seed.getPersonalData().setSeedId(seed);
        seed.getContactData().setSeedId(seed);
        seed.getFollowUp().setSeedId(seed);
        seed.getFollowUp().getStatus().setSeedFollowUpId(seed.getFollowUp());
        seed.getPostulationData().setSeedId(seed);
        seedRepository.save(seed);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void addSeeds(List<Seed> seeds) {
        seeds.stream().forEach(this::addSeed);
    }

    @Override
    public SeedDto updateSeed(SeedDto dto, String id) throws Exception {
        try {
            dto.setId(id);
            Seed seed = seedConverter.dtoToEntity(dto);
            seedRepository.save(seed);
            if(dto.getProjectNames() != null) asignProjectListToSeed(seed, dto.getProjectNames());
            return seedConverter.entityToDto(seed);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            System.err.println(ex.toString());
            return null;
        }
    }

    @Override
    public SeedDto findSeedById(String id) {
        return seedConverter.entityToDto(seedRepository.findById(id).get());
    }

    //Event
    @Override
    public Seed findSeedByDni(Integer dni) {
        return seedRepository.getSeedByDni(dni).get(0);
    }

    @Override
    public Seed findSeed(String id) {
        return seedRepository.findSeedById(id);
    }

    @Override
    public void deleteSeed(String id) {
        Seed seed = seedRepository.findById(id).get();
        seed.setActive(false);
        seedRepository.save(seed);
    }

    @Override
    public Integer getSeedAttendance(String seedId) {
        try {
            return seedAttendanceRepository.getSeedAttendance(seedId);
        }catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    @Override
    public Integer getInvitedTo(String seedId) {
        try {
            return seedAttendanceRepository.getInvitedTo(seedId);
        }catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<String> listOfCountry() {
        List <String> countries = entityManager.createNativeQuery(seedQueries.listOfCountry()).getResultList();
        return countries;
    }

    @Override
    public List<String> ListOfProjectActive() {
        return entityManager.createNativeQuery(seedQueries.ListOfProjectActive()).getResultList();
    }

    @Override
    public List<SeedDto> filteredSeed(SeedFilter seedfilter) {
        List<Seed> seeds;
        seeds= entityManager.createNativeQuery(SeedQueries.filterSeed(seedfilter),Seed.class).getResultList();

        return seedConverter.entitiesToDto(seeds);
    }

    @Override
    public List<String> listOfCommision() {
     return entityManager.createNativeQuery(seedQueries.listCommissionSeedActive()).getResultList();
    }

    //@Override
    public Seed asignProjectToSeed(Seed seed, Project project) {
        try {
            if(project != null && seed != null && isProjectAsignedToSeed(project, seed)) {
                ProjectSeed projectSeed = new ProjectSeed();
                projectSeed.setSeed(seed);
                projectSeed.setProject(project);
                projectSeedRepository.save(projectSeed);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return seed;

    }

    private void compareProjectsNamesToProjectList(String seedId, List<String> projectNames) {
        List<ProjectSeed> projectList = projectService.findProjectsListBySeedId(seedId);
        for (ProjectSeed projectSeed : projectList) {
            if(!projectNames.contains(projectSeed.getProject().getProjectName())) {
                projectSeed.setIsActive(false);
                projectSeedRepository.save(projectSeed);
            }
        }
    }
    public void asignProjectListToSeed(Seed seed, List<String> projectNames) {
        try {
            for (String name : projectNames) {
                Project project = projectService.findProjectByName(name);
                asignProjectToSeed(seed, project);
            }
            compareProjectsNamesToProjectList(seed.getId(), projectNames);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Boolean isProjectAsignedToSeed(Project project, Seed seed) {
        ProjectSeed projectSeed = projectSeedRepository.findAsignedSeedToProject(project.getId(), seed.getId());
        return projectSeed == null;
    }


}
