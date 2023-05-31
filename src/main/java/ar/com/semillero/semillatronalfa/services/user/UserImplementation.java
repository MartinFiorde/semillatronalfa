package ar.com.semillero.semillatronalfa.services.user;

import ar.com.semillero.semillatronalfa.entities.user.User;
import ar.com.semillero.semillatronalfa.repositories.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserImplementation implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User matchUser(String username, String password) {
        return userRepository.matchUserInDB(username, password).orElse(null);
    }
}
