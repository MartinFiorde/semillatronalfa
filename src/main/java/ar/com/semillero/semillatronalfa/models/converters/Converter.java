package ar.com.semillero.semillatronalfa.models.converters;

import java.util.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;

public abstract class Converter<E, D> {

    protected ModelMapper modelMapper;

    @Autowired
    protected Converter() {
        this.modelMapper = new ModelMapper();
    }

    protected abstract D entityToDto(E entity);

    protected abstract E dtoToEntity(D dto) throws ParseException;

    protected List<D> entitiesToDto(List<E> entities) {
        List<D> list = new ArrayList<>();
        entities.forEach((aux) -> list.add(entityToDto(aux)));
        return list;
    }

    protected List<E> dtosToEntities(List<D> dtos) {
        List<E> list = new ArrayList<>();
        dtos.forEach((aux) -> list.add(dtoToEntity(aux)));
        return list;
    }
}
