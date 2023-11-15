package ar.com.semillero.semillatronalfa.models.converters;

import ar.com.semillero.semillatronalfa.models.dtos.ProjectDto;
import ar.com.semillero.semillatronalfa.models.project.*;
import ar.com.semillero.semillatronalfa.repositories.ProjectRepository;
import org.springframework.expression.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProjectConverter extends Converter<Project, ProjectDto> {

    private ProjectRepository projectRepository;

    @Autowired
    public ProjectConverter(ProjectRepository projectRepository) {
        super();
        this.projectRepository = projectRepository;
    }

    @Override
    public ProjectDto entityToDto(Project entity) {

        // PROJECT
        ProjectDto dto = modelMapper.map(entity, ProjectDto.class);  // APLICA SOLO A LAS VARIABLES DIRECTAS DE PROJECT Y A LIST<SEED>, NO A VARIABLES CONTENIDAS EN SUBCALSES
        // PROJECT DETAILS
        if(entity.getDetails() != null) {
            dto.setPurpose(entity.getDetails().getPurpose());
            dto.setProjectType(entity.getDetails().getProjectType());
            dto.setProjectStatus(entity.getDetails().getProjectStatus());
            dto.setProjectStage(entity.getDetails().getProjectStage());
            dto.setObservations(entity.getDetails().getObservations());
            dto.setInitialDate(entity.getDetails().getInitialDate());
            dto.setEndingDate(entity.getDetails().getEndingDate());
            dto.setEstimatedTime(entity.getDetails().getEstimatedTime());
        }
        // MANAGEMENT TEAM
        if(entity.getManagementTeam() != null) {
            dto.setProductOwnerFullName(entity.getManagementTeam().getProductOwnerFullName());
            dto.setProductOwnerEmail(entity.getManagementTeam().getProductOwnerEmail());
            dto.setProductOwnerTelephone(entity.getManagementTeam().getProductOwnerTelephone());
            dto.setProductOwnerDiscordUser(entity.getManagementTeam().getProductOwnerDiscordUser());

            dto.setProjectManagerFullName(entity.getManagementTeam().getProjectManagerFullName());
            dto.setProjectManagerEmail(entity.getManagementTeam().getProjectManagerEmail());
            dto.setProjectManagerTelephone(entity.getManagementTeam().getProjectManagerTelephone());
            dto.setProjectManagerDiscordUser(entity.getManagementTeam().getProjectManagerDiscordUser());

            dto.setTeamLeaderFullName(entity.getManagementTeam().getTeamLeaderFullName());
            dto.setTeamLeaderEmail(entity.getManagementTeam().getTeamLeaderEmail());
            dto.setTeamLeaderTelephone(entity.getManagementTeam().getTeamLeaderTelephone());
            dto.setTeamLeaderDiscordUser(entity.getManagementTeam().getTeamLeaderDiscordUser());
        }
        if(entity.getCompanyData() != null) {
            dto.setAlly(entity.getCompanyData().getAlly());
            dto.setAddress(entity.getCompanyData().getAddress());
            dto.setResponsible(entity.getCompanyData().getResponsible());
            dto.setResponsibleEmail(entity.getCompanyData().getResponsibleEmail());
            dto.setResponsiblePhoneNumber(entity.getCompanyData().getResponsiblePhoneNumber());
        }
        return dto;
    }

    @Override
    public Project dtoToEntity(ProjectDto dto) throws ParseException {
        Project project = modelMapper.map(dto, Project.class);
        ProjectDetails details = modelMapper.map(dto, ProjectDetails.class);
        ManagementTeam managementTeam = modelMapper.map(dto, ManagementTeam.class);
        CompanyData companyData = modelMapper.map(dto, CompanyData.class);
        project.setDetails(details);
        project.setManagementTeam(managementTeam);
        project.setCompanyData(companyData);
        details.setProject(project);
        managementTeam.setProject(project);
        companyData.setProject(project);

        return project;
    }

    @Override
    public List<ProjectDto> entitiesToDto(List<Project> entities) {
        return super.entitiesToDto(entities);
    }

    @Override
    public List<Project> dtosToEntities(List<ProjectDto> dtos) {
        return dtos.stream().map(projectDto -> dtoToEntity(projectDto)).collect(Collectors.toList());
    }
}
