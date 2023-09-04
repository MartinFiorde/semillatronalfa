/*
package ar.com.semillero.semillatronalfa.utils.deprecated;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommissionRepository extends JpaRepository<Commission, Object> {

    @Query(value = "SELECT * FROM commission WHERE commission.is_active = 1", nativeQuery = true)
    List<Commission> getAllActives();
    
    @Query(value = "SELECT * FROM commission WHERE commission.is_active = 1", nativeQuery = true)
    Commission getActiveCommission();
    
    @Query(value = "SELECT * FROM commission WHERE commission.name = :name", nativeQuery = true)
    Optional<Commission> findByName(@Param("name") String name);
    
    @Query(value = "UPDATE commission SET commission.is_active = TRUE WHERE commission.id LIKE :id", nativeQuery = true)
    void setActiveById(@Param("id") String id);
    
    @Query(value = "UPDATE commission SET commission.is_active = FALSE WHERE commission.id LIKE :id", nativeQuery = true)
    void setInactiveById(@Param("id") String id);

}
*/