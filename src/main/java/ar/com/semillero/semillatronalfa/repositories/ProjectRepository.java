package ar.com.semillero.semillatronalfa.repositories;

import ar.com.semillero.semillatronalfa.models.project.ProjectSeed;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ar.com.semillero.semillatronalfa.models.project.Project;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, String> {
	@Query(value = "select * from project", nativeQuery = true)
    List<Project> getProjectList();

    @Query(value = "SELECT distinct commission FROM project p WHERE p.is_active = 1 ORDER BY commission ASC;", nativeQuery = true)
    List<String> listOfProjectCommissions();

    @Query(value = "SELECT * FROM project p WHERE p.is_active = 1 and p.id = :id", nativeQuery = true)
    Project findProjectById(@Param("id") String id);

    @Query(value = "SELECT * FROM project p WHERE p.is_active = 1 and p.project_name = :projectName", nativeQuery = true)
    Project findProjectByName(@Param("projectName") String projectName);

    @Query(value = "select p.* from project_seed ps inner join project p on p.id = ps.project_id inner join seed s on s.id = ps.seed_id " +
            "where ps.is_active and s.id = :seed_id", nativeQuery = true)
    List<Project> findAsignedSeedListToProject(@Param("seed_id") String seedId);
    
}