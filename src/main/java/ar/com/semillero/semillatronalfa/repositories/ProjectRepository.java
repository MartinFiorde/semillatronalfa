package ar.com.semillero.semillatronalfa.repositories;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import ar.com.semillero.semillatronalfa.models.project.Project;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, String> {
	@Query(value = "select * from project", nativeQuery = true)
    List<Project> getProjectList();

    @Query(value = "SELECT distinct commission FROM project p WHERE p.is_active = 1 ORDER BY commission ASC;", nativeQuery = true)
    List<String> listOfProjectCommissions();
    
}