/*
package ar.com.semillero.semillatronalfa.utils.deprecated;

import ar.com.semillero.semillatronalfa.models.project.ManagementTeam;
import ar.com.semillero.semillatronalfa.models.project.Project;
import ar.com.semillero.semillatronalfa.models.project.ProjectDetails;

public class ProjectMethods {
    public static void matchLoadedDataWithProject(ProjectDataLoading projectDataLoading, Project project) {

        project.setProjectName(projectDataLoading.getProjectName());
        project.setCommission(projectDataLoading.getCommission());
        project.setProduction(projectDataLoading.getProduction());
        project.setOriginPlace(projectDataLoading.getOriginPlace());
        project.setOds(projectDataLoading.getOds());

        project.getDetails().setPurpose(projectDataLoading.getPurpose());
        project.getDetails().setProjectType(projectDataLoading.getProjectType());
        project.getDetails().setProjectStatus(projectDataLoading.getProjectStatus());
        project.getDetails().setProjectStage(projectDataLoading.getProjectStage());
        project.getDetails().setObservations(projectDataLoading.getObservations());
        project.getDetails().setInitialDate(projectDataLoading.getInitialDate());
        project.getDetails().setEndingDate(projectDataLoading.getEndingDate());
        project.getDetails().setEstimatedTime(projectDataLoading.getEstimatedTime());

//        project.getManagementTeam().setProductOwner(projectDataLoading.getProductOwner());
//        project.getManagementTeam().setProjectManager(projectDataLoading.getProjectManager());
//        project.getManagementTeam().setEmailProductOwner(projectDataLoading.getEmailProductOwner());
//        project.getManagementTeam().setTelephoneProductOwner(projectDataLoading.getTelephoneProductOwner());
//        project.getManagementTeam().setScrum(projectDataLoading.getScrum());
    }

    public static Project createProject(ProjectDataLoading projectDataLoading, Project project) {
        ProjectDetails details = new ProjectDetails();
        ManagementTeam managementTeam = new ManagementTeam();
        project.setProjectName(projectDataLoading.getProjectName());
        project.setCommission(projectDataLoading.getCommission());
        project.setProduction(projectDataLoading.getProduction());
        project.setOriginPlace(projectDataLoading.getOriginPlace());
        project.setOds(projectDataLoading.getOds());

        details.setPurpose(projectDataLoading.getPurpose());
        details.setProjectType(projectDataLoading.getProjectType());
        details.setProjectStatus(projectDataLoading.getProjectStatus());
        details.setProjectStage(projectDataLoading.getProjectStage());
        details.setObservations(projectDataLoading.getObservations());
        details.setInitialDate(projectDataLoading.getInitialDate());
        details.setEndingDate(projectDataLoading.getEndingDate());
        details.setEstimatedTime(projectDataLoading.getEstimatedTime());

//        managementTeam.setProductOwner(projectDataLoading.getProductOwner());
//        managementTeam.setProjectManager(projectDataLoading.getProjectManager());
//        managementTeam.setEmailProductOwner(projectDataLoading.getEmailProductOwner());
//        managementTeam.setTelephoneProductOwner(projectDataLoading.getTelephoneProductOwner());
//        managementTeam.setScrum(projectDataLoading.getScrum());

        project.setDetails(details);
        project.setManagementTeam(managementTeam);

        return project;
    }
}
*/