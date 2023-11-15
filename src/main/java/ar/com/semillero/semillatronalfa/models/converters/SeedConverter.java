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
        if(entity.getContactData() != null) {
            dto.setEmail(entity.getContactData().getEmail());
            dto.setTelephone(entity.getContactData().getTelephone());
            dto.setLinkedin(entity.getContactData().getLinkedin());
            dto.setDiscordUser(entity.getContactData().getDiscordUser());
        }
        // POSTULATION DATA
        if(entity.getPostulationData() != null) {
            dto.setRol(entity.getPostulationData().getRol());
            dto.setTurn(entity.getPostulationData().getTurn());
            dto.setMeetSemilleroBy(entity.getPostulationData().getMeetSemilleroBy());
            dto.setStudies(entity.getPostulationData().getStudies());
            dto.setHobbies(entity.getPostulationData().getHobbies());
            dto.setComment(entity.getPostulationData().getComment());
        }
        // PERSONAL DATA
        if(entity.getPersonalData() != null) {
            dto.setFirstName(entity.getPersonalData().getFirstName());
            dto.setLastName(entity.getPersonalData().getLastName());
            dto.setDni(entity.getPersonalData().getDni());
            dto.setBirthDate(entity.getPersonalData().getBirthDate());
            dto.setCountry(entity.getPersonalData().getCountry());
            dto.setCity(entity.getPersonalData().getCity());
            dto.setGender(entity.getPersonalData().getGender());
        }
        // FOLLOW UP
        if(entity.getFollowUp() != null) {
            dto.setPostulationDate(entity.getFollowUp().getPostulationDate());
            dto.setCommission(entity.getFollowUp().getCommission());
            dto.setRecommendationString(entity.getFollowUp().getRecommendationString());
            dto.setRecommendationComment(entity.getFollowUp().getRecommendationComment());
            dto.setCertificationString(entity.getFollowUp().getCertificationString());
            dto.setObservation(entity.getFollowUp().getObservation());
            dto.setStartDate(entity.getFollowUp().getStartDate());
            dto.setEndDate(entity.getFollowUp().getEndDate());
        }
        // SEED STATUS
        if(entity.getFollowUp().getStatus() != null) {
            dto.setPrimary(entity.getFollowUp().getStatus().getPrimary());
            dto.setSecondary(entity.getFollowUp().getStatus().getSecondary());
            dto.setProjectId(entity.getFollowUp().getStatus().getProjectId());
            dto.setProjectName(entity.getFollowUp().getStatus().getProjectName());
            dto.setDigitalEmployment(entity.getFollowUp().getStatus().getDigitalEmployment());
            dto.setCompanyName(entity.getFollowUp().getStatus().getCompanyName());
        }
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
        if(dto.getEmail() != null && !dto.getEmail().isEmpty()) {
            entity.getContactData().setEmail(dto.getEmail());
        }
        if(dto.getTelephone() != null && !dto.getTelephone().isEmpty()) {
            entity.getContactData().setTelephone(dto.getTelephone());
        }
        if(dto.getLinkedin() != null && !dto.getLinkedin().isEmpty()){
            entity.getContactData().setLinkedin(dto.getLinkedin());
        }
        if(dto.getDiscordUser() != null && !dto.getDiscordUser().isEmpty()){
            entity.getContactData().setDiscordUser(dto.getDiscordUser());
        }
        // POSTULATION DATA
        if(dto.getRol() != null && !dto.getRol().isEmpty()) {
            entity.getPostulationData().setRol(dto.getRol());
        }
        if(dto.getTurn() != null && !dto.getTurn().isEmpty()) {
            entity.getPostulationData().setTurn(dto.getTurn());
        }
        if(dto.getMeetSemilleroBy() != null && !dto.getMeetSemilleroBy().isEmpty()) {
            entity.getPostulationData().setMeetSemilleroBy(dto.getMeetSemilleroBy());
        }
        if(dto.getStudies() != null && !dto.getStudies().isEmpty()) {
            entity.getPostulationData().setStudies(dto.getStudies());
        }
        if(dto.getHobbies() != null && !dto.getHobbies().isEmpty()) {
            entity.getPostulationData().setHobbies(dto.getHobbies());
        }
        if(dto.getComment() != null && !dto.getComment().isEmpty()) {
            entity.getPostulationData().setComment(dto.getComment());
        }
        // PERSONAL DATA
        if(dto.getFirstName() != null && !dto.getFirstName().isEmpty()) {
            entity.getPersonalData().setFirstName(dto.getFirstName());
        }
        if(dto.getLastName() != null && !dto.getLastName().isEmpty()) {
            entity.getPersonalData().setLastName(dto.getLastName());
        }
        if(dto.getDni() != null) {
            entity.getPersonalData().setDni(dto.getDni());
        }
        if(dto.getBirthDate() != null) {
            entity.getPersonalData().setBirthDate(dto.getBirthDate());
        }
        if(dto.getCountry() != null && !dto.getCountry().isEmpty()) {
            entity.getPersonalData().setCountry(dto.getCountry());
        }
        if(dto.getCity() != null && !dto.getCity().isEmpty()) {
            entity.getPersonalData().setCity(dto.getCity());
        }
        if(dto.getGender() != null && !dto.getGender().isEmpty()) {
            entity.getPersonalData().setGender(dto.getGender());
        }
        // FOLLOW UP
        if(dto.getPostulationDate() != null) {
            entity.getFollowUp().setPostulationDate(dto.getPostulationDate());
        }
        if(dto.getCommission() != null && !dto.getCommission().isEmpty()) {
            entity.getFollowUp().setCommission(dto.getCommission());
        }
        if(dto.getRecommendationString() != null && !dto.getRecommendationString().isEmpty()) {
            entity.getFollowUp().setRecommendationString(dto.getRecommendationString());
        }
        if(dto.getRecommendationComment() != null && !dto.getRecommendationComment().isEmpty()) {
            entity.getFollowUp().setRecommendationComment(dto.getRecommendationComment());
        }
        if(dto.getCertificationString() != null && !dto.getCertificationString().isEmpty()) {
            entity.getFollowUp().setCertificationString(dto.getCertificationString());
        }
        if(dto.getObservation() != null && !dto.getObservation().isEmpty()) {
            entity.getFollowUp().setObservation(dto.getObservation());
        }
        if(dto.getStartDate() != null) {
            entity.getFollowUp().setStartDate(dto.getStartDate());
        }
        if(dto.getEndDate() != null) {
            entity.getFollowUp().setEndDate(dto.getEndDate());
        }
        // SEED STATUS
        if(dto.getPrimary() != null && !dto.getPrimary().isEmpty()) {
            entity.getFollowUp().getStatus().setPrimary(dto.getPrimary());
        }
//        if(dto.getSecondary() != null && !dto.getSecondary().isEmpty()) {
        if(dto.getSecondary() != null) {
            entity.getFollowUp().getStatus().setSecondary(dto.getSecondary());
        }
        if(dto.getProjectId() != null && !dto.getProjectId().isEmpty()) {
            entity.getFollowUp().getStatus().setProjectId(dto.getProjectId());
        }
        if(dto.getProjectName() != null && !dto.getProjectName().isEmpty()) {
            entity.getFollowUp().getStatus().setProjectName(dto.getProjectName());
        }
        if(dto.getDigitalEmployment() != null) {
            entity.getFollowUp().getStatus().setDigitalEmployment(dto.getDigitalEmployment());
        }
        if(dto.getCompanyName() != null && !dto.getCompanyName().isEmpty()) {
            entity.getFollowUp().getStatus().setCompanyName(dto.getCompanyName());
        }
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
