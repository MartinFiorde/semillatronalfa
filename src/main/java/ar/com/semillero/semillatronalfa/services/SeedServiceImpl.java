package ar.com.semillero.semillatronalfa.services;

import ar.com.semillero.semillatronalfa.models.converters.SeedConverter;
import ar.com.semillero.semillatronalfa.services.interfaces.SeedService;
import ar.com.semillero.semillatronalfa.models.dtos.SeedDto;
import ar.com.semillero.semillatronalfa.models.filters.SeedFilter;
import ar.com.semillero.semillatronalfa.models.seed.Seed;
import ar.com.semillero.semillatronalfa.repositories.queries.SeedQueries;
import ar.com.semillero.semillatronalfa.repositories.SeedRepository;
import ar.com.semillero.semillatronalfa.utils.NullUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class SeedServiceImpl implements SeedService {

    private SeedRepository seedRepository;
    private SeedQueries seedQueries;
    private SeedConverter seedConverter;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public SeedServiceImpl(SeedRepository seedRepository, SeedQueries seedQueries, SeedConverter seedConverter, EntityManager entityManager) {
        this.seedRepository = seedRepository;
        this.seedQueries = seedQueries;
        this.seedConverter = seedConverter;
        this.entityManager = entityManager;
        
    }

    @Override
    public List<Seed> findAllSeeds() {
        return seedRepository.findAll();
    }

    @Override
    public List<SeedDto> orderedSeedList(Optional<String> statusFilter, Optional<String> commissionOrder, Optional<String> lastNameOrder) {
        return seedConverter.entitiesToDto(entityManager.createNativeQuery(seedQueries.orderedSeedList(statusFilter, commissionOrder, lastNameOrder), Seed.class).getResultList());
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
        return seedRepository.getSeedByDni(dni);
    }

    @Override
    public void deleteSeed(String id) {
        Seed seed = seedRepository.findById(id).get();
        seed.setActive(false);
        seedRepository.save(seed);
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
        if (!NullUtils.nullOrEmpty(seedfilter.getSearchBar())) {
            seeds = entityManager.createNativeQuery(SeedQueries.seachBarSeed2(seedfilter),Seed.class).getResultList();
        }else{
            seeds= entityManager.createNativeQuery(SeedQueries.filterSeed(seedfilter),Seed.class).getResultList();
        }   
     //List<Seed> seeds= entityManager.createNativeQuery(SeedQueries.filterSeed(seedfilter),Seed.class).getResultList();   
     //List<Seed> seeds= entityManager.createQuery(SeedQueries.filterSeed(seedfilter)).getResultList();
     return seedConverter.entitiesToDto(seeds);
    }

    @Override
    public List<String> listOfCommision() {
     return entityManager.createNativeQuery(seedQueries.listCommissionSeedActive()).getResultList();
    }
        
}
