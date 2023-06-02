package ar.com.semillero.semillatronalfa.services.seed;

import ar.com.semillero.semillatronalfa.entities.seed.Seed;
import ar.com.semillero.semillatronalfa.repositories.seed.SeedRepository;
import ar.com.semillero.semillatronalfa.services.commission.CommissionImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SeedImplementation implements SeedService {

    private SeedRepository seedRepository;
    private CommissionImplementation commissionImplementation;

    @Autowired
    public SeedImplementation(SeedRepository seedRepository, CommissionImplementation commissionImplementation) {
        this.seedRepository = seedRepository;
        this.commissionImplementation = commissionImplementation;
    }

    @Override
    public List<Seed> findAllSeeds() {
        return seedRepository.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void addSeed(Seed seed) {
        seed.setCommission(commissionImplementation.findCommissionByName(seed.getCommission().getName()));
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
        for (Seed seed : seeds) {
            addSeed(seed);
        }
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
