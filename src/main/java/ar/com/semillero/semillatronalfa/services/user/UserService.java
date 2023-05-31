package ar.com.semillero.semillatronalfa.services.user;


import ar.com.semillero.semillatronalfa.entities.user.User;

public interface UserService {
    User matchUser(String username, String password);
}
