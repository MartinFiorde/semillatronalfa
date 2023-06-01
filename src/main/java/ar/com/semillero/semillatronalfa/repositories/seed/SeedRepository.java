package ar.com.semillero.semillatronalfa.repositories.seed;

import ar.com.semillero.semillatronalfa.entities.seed.Seed;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository // Querys con condicional LIKE: https://stackoverflow.com/questions/14908142/sql-like-search-string-starts-with
public interface SeedRepository extends JpaRepository<Seed, Object> {

//    @Query("SELECT a FROM Seed a WHERE a.personal_data.dni = :dni")
//    public Optional<Seed> findSeedByDni(@Param("dni") Integer dni);

}
