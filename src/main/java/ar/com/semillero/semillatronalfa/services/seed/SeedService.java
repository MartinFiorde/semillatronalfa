package ar.com.semillero.semillatronalfa.services.seed;

import ar.com.semillero.semillatronalfa.entities.seed.Seed;

import java.util.List;

public interface SeedService {

    List<Seed> findAllSeeds();

    void addSeed(Seed seed);

    Seed findSeedById(String id);
    
    Seed findSeedByDni(Integer dni);
    
}

