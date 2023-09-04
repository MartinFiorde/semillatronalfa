package ar.com.semillero.semillatronalfa.services.interfaces;

import ar.com.semillero.semillatronalfa.models.dtos.SeedDto;
import ar.com.semillero.semillatronalfa.models.filters.SeedFilter;
import ar.com.semillero.semillatronalfa.models.seed.Seed;

import java.util.List;
import java.util.Optional;

public interface SeedService {

    List<Seed> findAllSeeds();

    List<SeedDto> orderedSeedList(Optional<String> statusFilter, Optional<String> commissionOrder, Optional<String> lastNameOrder);

    List<SeedDto> filteredSeedList(SeedFilter seedFilter, Optional<String> commissionO, Optional<String> lastNameO);

    void addSeed(Seed seed);
    
    void addSeeds(List<Seed> seeds);

    SeedDto findSeedById(String id);
    
    Seed findSeedByDni(Integer dni);
    
    SeedDto updateSeed(SeedDto dto, String id) throws Exception ;
    
    void deleteSeed(String id);
    
    List<String> listOfCountry();
    
    List<String> ListOfProjectActive();
    
    List<SeedDto> filteredSeed ( SeedFilter seedfilter);
    
    List<String> listOfCommision();
}

