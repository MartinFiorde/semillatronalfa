package ar.com.semillero.semillatronalfa.models.converters;

import ar.com.semillero.semillatronalfa.models.dtos.ProjectDto;
import ar.com.semillero.semillatronalfa.models.dtos.SeedDto;
import ar.com.semillero.semillatronalfa.models.project.Project;
import ar.com.semillero.semillatronalfa.models.seed.Seed;
import ar.com.semillero.semillatronalfa.models.seed.SeedContactData;
import ar.com.semillero.semillatronalfa.models.seed.SeedFollowUp;
import ar.com.semillero.semillatronalfa.models.seed.SeedPersonalData;
import ar.com.semillero.semillatronalfa.models.seed.SeedPostulationData;
import ar.com.semillero.semillatronalfa.models.seed.SeedStatus;
import ar.com.semillero.semillatronalfa.repositories.SeedRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Component;

@Component
public class SeedConverter extends Converter<Seed, SeedDto> {

    private SeedRepository seedRepository;

    @Autowired
    public SeedConverter(SeedRepository seedRepository) {
        super();
        this.seedRepository = seedRepository;
    }

    @Override
    public SeedDto entityToDto(Seed entity) {
        // SEED
        SeedDto dto = modelMapper.map(entity, SeedDto.class);  // APLICA SOLO A LAS VARIABLES DIRECTAS DE PROJECT Y A LIST<SEED>, NO A VARIABLES CONTENIDAS EN SUBCALSES

        // CONTACT DATA
        dto.setEmail(entity.getContactData().getEmail());
        dto.setTelephone(entity.getContactData().getTelephone());
        dto.setLinkedin(entity.getContactData().getLinkedin());
        dto.setDiscordUser(entity.getContactData().getDiscordUser());

        // POSTULATION DATA
        dto.setRol(entity.getPostulationData().getRol());
        dto.setTurn(entity.getPostulationData().getTurn());
        dto.setMeetSemilleroBy(entity.getPostulationData().getMeetSemilleroBy());
        dto.setStudies(entity.getPostulationData().getStudies());
        dto.setHobbies(entity.getPostulationData().getHobbies());
        dto.setComment(entity.getPostulationData().getComment());

        // PERSONAL DATA
        dto.setFirstName(entity.getPersonalData().getFirstName());
        dto.setLastName(entity.getPersonalData().getLastName());
        dto.setDni(entity.getPersonalData().getDni());
        dto.setBirthDate(entity.getPersonalData().getBirthDate());
        dto.setCountry(entity.getPersonalData().getCountry());
        dto.setCity(entity.getPersonalData().getCity());
        dto.setGender(entity.getPersonalData().getGender());

        // FOLLOW UP
        dto.setPostulationDate(entity.getFollowUp().getPostulationDate());
        dto.setCommission(entity.getFollowUp().getCommission());
        dto.setRecommendationString(entity.getFollowUp().getRecommendationString());
        dto.setRecommendationComment(entity.getFollowUp().getRecommendationComment());
        dto.setCertificationString(entity.getFollowUp().getCertificationString());
        dto.setObservation(entity.getFollowUp().getObservation());

        // SEED STATUS
        dto.setPrimary(entity.getFollowUp().getStatus().getPrimary());
        dto.setSecondary(entity.getFollowUp().getStatus().getSecondary());
        dto.setProjectId(entity.getFollowUp().getStatus().getProjectId());
        dto.setProjectName(entity.getFollowUp().getStatus().getProjectName());
        dto.setDigitalEmployment(entity.getFollowUp().getStatus().getDigitalEmployment());
        dto.setCompanyName(entity.getFollowUp().getStatus().getCompanyName());

        return dto;
    }

    @Override
    public Seed dtoToEntity(SeedDto dto) throws ParseException {

        Seed entity = new Seed();
        entity.setContactData(new SeedContactData());
        entity.setPersonalData(new SeedPersonalData());
        entity.setPostulationData(new SeedPostulationData());
        entity.setFollowUp(new SeedFollowUp());
        entity.getFollowUp().setStatus(new SeedStatus());
        entity = seedRepository.findById(dto.getId()).orElse(entity);
        //entity = modelMapper.map(dto, Seed.class); // NO APLICA POR TENER DIFERENTES ESTRUCTURAS DE CLASES/ DATOS Y CONTEMPLAR CAMBIOS DE UN POSIBLE ENTITY YA PREARMADO

        // CONTACT DATA
        entity.getContactData().setEmail(dto.getEmail());
        entity.getContactData().setTelephone(dto.getTelephone());
        entity.getContactData().setLinkedin(dto.getLinkedin());
        entity.getContactData().setDiscordUser(dto.getDiscordUser());

        // POSTULATION DATA
        entity.getPostulationData().setRol(dto.getRol());
        entity.getPostulationData().setTurn(dto.getTurn());
        entity.getPostulationData().setMeetSemilleroBy(dto.getMeetSemilleroBy());
        entity.getPostulationData().setStudies(dto.getStudies());
        entity.getPostulationData().setHobbies(dto.getHobbies());
        entity.getPostulationData().setComment(dto.getComment());

        // PERSONAL DATA
        entity.getPersonalData().setFirstName(dto.getFirstName());
        entity.getPersonalData().setLastName(dto.getLastName());
        entity.getPersonalData().setDni(dto.getDni());
        entity.getPersonalData().setBirthDate(dto.getBirthDate());
        entity.getPersonalData().setCountry(dto.getCountry());
        entity.getPersonalData().setCity(dto.getCity());
        entity.getPersonalData().setGender(dto.getGender());

        // FOLLOW UP
        entity.getFollowUp().setPostulationDate(dto.getPostulationDate());
        entity.getFollowUp().setCommission(dto.getCommission());
        entity.getFollowUp().setRecommendationString(dto.getRecommendationString());
        entity.getFollowUp().setRecommendationComment(dto.getRecommendationComment());
        entity.getFollowUp().setCertificationString(dto.getCertificationString());
        entity.getFollowUp().setObservation(dto.getObservation());

        // SEED STATUS
        entity.getFollowUp().getStatus().setPrimary(dto.getPrimary());
        entity.getFollowUp().getStatus().setSecondary(dto.getSecondary());
        entity.getFollowUp().getStatus().setProjectId(dto.getProjectId());
        entity.getFollowUp().getStatus().setProjectName(dto.getProjectName());
        entity.getFollowUp().getStatus().setDigitalEmployment(dto.getDigitalEmployment());
        entity.getFollowUp().getStatus().setCompanyName(dto.getCompanyName());

        return entity;
    }
    
    @Override
    public List<SeedDto> entitiesToDto(List<Seed> entities) {
        return super.entitiesToDto(entities);
    }

    @Override
    public List<Seed> dtosToEntities(List<SeedDto> dtos) {
        return super.dtosToEntities(dtos);
    }
}
