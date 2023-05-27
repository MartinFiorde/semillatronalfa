package ar.com.semillero.semillatronalfa.repositories.Proyect;

import ar.com.semillero.semillatronalfa.entities.proyect.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, String> {
	@Query(value = "select * from project", nativeQuery = true)
    List<Project> getProjectList();

}