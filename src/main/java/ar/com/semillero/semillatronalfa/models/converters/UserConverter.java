package ar.com.semillero.semillatronalfa.models.converters;

import ar.com.semillero.semillatronalfa.models.dtos.UserDto;
import ar.com.semillero.semillatronalfa.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserConverter extends Converter<User, UserDto> {

    @Autowired
    public UserConverter() {
        super();
    }
     @Override
    public UserDto entityToDto(User entity) {
        UserDto dto = modelMapper.map(entity, UserDto.class);
        dto.setPassword(null);
        return dto;
    }

    @Override
    public User dtoToEntity(UserDto dto) throws ParseException {
        User entity = modelMapper.map(dto, User.class);
        if (dto.getPassword() != null) dto.setPassword(new BCryptPasswordEncoder().encode(dto.getPassword()));
        entity = modelMapper.map(dto, User.class);
        return entity;
    }

    @Override
    public List<UserDto> entitiesToDto(List<User> entities) {
        return super.entitiesToDto(entities);
    }

    @Override
    public List<User> dtosToEntities(List<UserDto> dtos) {
        return super.dtosToEntities(dtos);
    }

}