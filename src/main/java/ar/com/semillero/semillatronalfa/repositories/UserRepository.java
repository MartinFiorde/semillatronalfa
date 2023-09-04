package ar.com.semillero.semillatronalfa.repositories;

import ar.com.semillero.semillatronalfa.models.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    
    @Query(value = "SELECT * FROM user "
            + "WHERE user.active = 1", nativeQuery = true)
    public List<User> getAllActive();
    
    @Query(value = "select * from user where user.username = :username and password = :password", nativeQuery = true)
    Optional<User> matchUserInDB(@Param("username") String username, @Param("password") String password);
    
    public List<User> findByUsername(String search);
}
