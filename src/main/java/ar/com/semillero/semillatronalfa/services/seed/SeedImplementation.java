package ar.com.semillero.semillatronalfa.services.seed;

import ar.com.semillero.semillatronalfa.entities.seed.Seed;
import ar.com.semillero.semillatronalfa.repositories.seed.SeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SeedImplementation implements SeedService {

    private SeedRepository seedRepository;

    @Autowired
    public SeedImplementation(SeedRepository seedRepository) {
        this.seedRepository = seedRepository;
    }

    @Override
    public List<Seed> findAllSeeds() {
        return seedRepository.findAll();
    }

    @Override
    public void addSeed(Seed seed) {
    }

    @Override
    public Seed findSeedById(String id) {
        return seedRepository.findById(id).orElse(null);
    }

    @Override
    public Seed findSeedByDni(Integer dni) {
        //return seedRepository.findSeedByDni(dni).orElse(null);
        return null;
    }
    
}
