package ar.com.semillero.semillatronalfa.repositories;

import ar.com.semillero.semillatronalfa.models.project.ProjectSeed;
import ar.com.semillero.semillatronalfa.models.seed.Seed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectSeedRepository extends JpaRepository<ProjectSeed, String> {

    @Query(value = "select ps.* from project_seed ps inner join project p on p.id = ps.project_id inner join seed s on s.id = ps.seed_id " +
            "where ps.is_active and p.id = :project_id", nativeQuery = true)
    List<ProjectSeed> findAsignedSeedsToProject(@Param("project_id") String projectId);

    @Query(value = "select ps.* from project_seed ps inner join project p on p.id = ps.project_id inner join seed s on s.id = ps.seed_id " +
            "where ps.is_active and p.id = :project_id and s.id = :seed_id", nativeQuery = true)
    ProjectSeed findAsignedSeedToProject(@Param("project_id") String projectId, @Param("seed_id") String seedId);

    @Query(value = "select ps.* from project_seed ps inner join project p on p.id = ps.project_id inner join seed s on s.id = ps.seed_id " +
            "where ps.is_active and s.id = :seed_id", nativeQuery = true)
    List<ProjectSeed> findProjectsListBySeedId(@Param("seed_id") String seedId);
}
