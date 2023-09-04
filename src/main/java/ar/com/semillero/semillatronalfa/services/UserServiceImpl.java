package ar.com.semillero.semillatronalfa.services;

import ar.com.semillero.semillatronalfa.services.interfaces.UserService;
import ar.com.semillero.semillatronalfa.models.converters.UserConverter;
import java.util.*;
import ar.com.semillero.semillatronalfa.models.dtos.UserDto;
import ar.com.semillero.semillatronalfa.models.User;
import ar.com.semillero.semillatronalfa.configs.errors.ServiceRuntimeException;
import ar.com.semillero.semillatronalfa.repositories.UserRepository;
import ar.com.semillero.semillatronalfa.utils.NullUtils;
import java.util.regex.*;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.*;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    // INSTANCES AND CONSTRUCTOR
    private UserRepository userRepository;
    private UserConverter userConverter;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    // METHODS
    @Override
    public UserDto register(UserDto d, String passwordConfirm) throws ServiceRuntimeException, Exception {
        validateRegister(d, passwordConfirm);
        return save(d);
    }

    public boolean isFirstUser() {
        return userRepository.findAll().isEmpty();
    }
    
    @Override
    public UserDto save(UserDto d) throws ServiceRuntimeException {
        User user = userConverter.dtoToEntity(d);
        user = userRepository.save(user);
        return userConverter.entityToDto(user);
    }

    @Override
    public UserDto edit(UserDto d) throws ServiceRuntimeException {
        User userInDB = userRepository.findById(d.getId()).get();
        User userModified = userConverter.dtoToEntity(d);
        userModified.setActive(userInDB.isActive());
        userModified.setUsername(userInDB.getUsername());
        userModified.setPassword(userInDB.getPassword());
        validateUpdate(userConverter.entityToDto(userModified));
        return userConverter.entityToDto(userRepository.save(userModified));
    }

    public void editPassword(String password, String passwordNew, String passwordConfirm) throws ServiceRuntimeException {
        validPasswordSession(password);
        validatePasswords(passwordNew, passwordConfirm);
        User userInDB = userRepository.findById(sessionId()).orElse(null);
        userInDB.setPassword(new BCryptPasswordEncoder().encode(passwordNew));
        userRepository.save(userInDB);
    }
    
    @Override
    public UserDto getOne(String id) throws ServiceRuntimeException {
        UserDto d = userConverter.entityToDto(userRepository.findById(id).orElse(null));
        if (d==null) throw new ServiceRuntimeException("User not found");
        return userConverter.entityToDto(userRepository.findById(id).orElse(null));
    }

    @Override
    public List<UserDto> getAllActives() {
        return userConverter.entitiesToDto(userRepository.getAllActive());
    }

    @Override
    public UserDto deactivate(String id) {
        User user = userRepository.findById(id).orElse(null);
        user.setActive(false);
        userRepository.save(user);
        return getOne(id);
    }

    @Override
    public UserDto activate(String id) throws ServiceRuntimeException {
        User user = userRepository.findById(id).orElse(null);
        user.setActive(true);
        userRepository.save(user);
        return getOne(id);
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).isEmpty() ? null : userRepository.findByUsername(username).get(0);
        if (user == null) throw new UsernameNotFoundException("User not found");
        if (!user.isActive()) throw new UsernameNotFoundException("User not active");
        List<GrantedAuthority> permissions = new ArrayList();
        GrantedAuthority rolePermissions = new SimpleGrantedAuthority("ROLE_EMPLOYEE");
        permissions.add(rolePermissions);

        // Save the user object in the session attributes
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);
        session.setAttribute("usersesion", user);

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), permissions);
    }
    
    public String sessionId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String pathmail = "";
        if (principal instanceof UserDetails) {
            pathmail = ((UserDetails) principal).getUsername();
        } else {
            pathmail = principal.toString();
        }
        String idsesion = userRepository.findByUsername(pathmail).get(0).getId();
        return idsesion;
    }
    
    public boolean validPasswordSession(String password) throws ServiceRuntimeException {
        if (!new BCryptPasswordEncoder().matches(password, userRepository.findById(sessionId()).get().getPassword())) {
            throw new ServiceRuntimeException("La clave anterior no es correcta");
        }
        return true;
    }
    
    public User findActiveById(String id) throws ServiceRuntimeException {
        return validateActiveStatus(userRepository.findById(id).orElse(null));
    }


    // VALIDATORS 
    public UserDto validateRegister(UserDto dto, String passwordConfirm) {
        validatePasswords(dto.getPassword(), passwordConfirm);
        validateGenericString(dto.getUsername());
        return dto;
    }

    public UserDto validateUpdate(UserDto dto) throws ServiceRuntimeException {
        validateGenericString(dto.getUsername());
        return dto;
    }

    public String validatePasswords(String password, String passwordConfirm) throws ServiceRuntimeException {
        if (NullUtils.nullOrEmpty(password)) {
            throw new ServiceRuntimeException("La clave no puede estar vacia.");
        }

        // Disable next line for faster manual testing creating and manipulating multiple accounts from scratch
        validateComplexPasswords(password);

        if (password.trim().length() < 4) {
            throw new ServiceRuntimeException("La clave debe tener más de 4 carácteres.");
        }
        if (!password.equals(passwordConfirm)) {
            throw new ServiceRuntimeException("Las claves no coinciden.");
        }
        return password;
    }

    public void validateComplexPasswords(String password) throws ServiceRuntimeException {
        // https://www.geeksforgeeks.org/how-to-validate-a-password-using-regular-expressions-in-java/
        // https://stackoverflow.com/questions/3802192/regexp-java-for-password-validation
        // FULL SPECIAL CHARACTERS SUPPORT - https://stackoverflow.com/questions/24442564/need-a-regex-for-password-validation-that-allows-all-special-characters
        Pattern p = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!-\\/:-@\\[-`{-~])(?=\\S+$).{8,20}$");
        
        Matcher m = p.matcher(password);
        if (!m.matches()) {
            throw new ServiceRuntimeException("La clave debe ser de 8-20 carácteres, sin espacios y contener minusculas, mayusculas, numeros y carácteres especiales.");
        }
    }

    public String validateGenericString(String string) throws ServiceRuntimeException {
        if (NullUtils.nullOrEmpty(string)) {
            throw new ServiceRuntimeException("No puede haber campos vacios.");
        }
        return string;
    }

    public User validateActiveStatus(User user) throws ServiceRuntimeException {
        if (!user.isActive()) {
            throw new ServiceRuntimeException("El usuario solicitado se encuentra deshabilitado.");
        }
        return user;
    }
}