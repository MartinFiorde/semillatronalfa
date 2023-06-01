package ar.com.semillero.semillatronalfa.repositories.user;

import ar.com.semillero.semillatronalfa.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    @Query(value = "select * from user where user.username = :username and password = :password", nativeQuery = true)
    Optional<User> matchUserInDB(@Param("username") String username, @Param("password") String password);
}
