package ar.com.semillero.semillatronalfa.repositories.Commission;

import ar.com.semillero.semillatronalfa.entities.Commission;
import ar.com.semillero.semillatronalfa.entities.seed.Seed;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommissionRepository extends JpaRepository<Commission, Object> {

    @Query("SELECT a FROM Commission a WHERE a.name = :name")
    public Optional<Seed> findCommissionByName(@Param("name") String name);

}
