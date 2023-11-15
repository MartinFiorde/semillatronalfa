package ar.com.semillero.semillatronalfa.repositories;

import ar.com.semillero.semillatronalfa.models.project.ProjectSeed;
import ar.com.semillero.semillatronalfa.models.seed.Seed;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository // Querys con condicional LIKE: https://stackoverflow.com/questions/14908142/sql-like-search-string-starts-with
public interface SeedRepository extends JpaRepository<Seed, Object> {

    @Query(value = "select * from seed where seed.is_active = 1", nativeQuery = true)
    List<Seed> getSeedList();
    
    @Query(value = "select * from seed inner join seed_personal_data on seed.id = seed_personal_data.seed_id_id where seed.is_active = 1 and seed_personal_data.dni = :dni", nativeQuery = true)
    List<Seed> getSeedByDni(@Param("dni") Integer dni);

    @Query(value = "select s.* from seed s inner join seed_personal_data spd on spd.seed_id_id = s.id " +
            "inner join seed_contact_data scd on scd.seed_id_id = s.id inner join seed_follow_up sfu on sfu.seed_id_id = s.id " +
            "inner join seed_postulation_data spdata on spdata.seed_id_id = s.id where s.id = :id", nativeQuery = true)
    Seed findSeedById(@Param("id") String id);

    @Query(value = "SELECT s.* FROM seed s INNER JOIN project p ON p.id = s.project_id WHERE p.id = :id", nativeQuery = true)
    List<Seed> findAsignedSeedsToProject(@Param("id") String id);
    
    /*@Query(value = "select distinct country from seed_personal_data inner join seed on seed.id = seed_personal_data.seed_id_id "+
            "where seed.is_active = 1 order by seed_personal_data.country asc", nativeQuery = true)
    public List<String> listOfCountry();*/
    
}
