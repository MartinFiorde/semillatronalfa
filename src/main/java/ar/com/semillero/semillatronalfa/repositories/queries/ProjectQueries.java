package ar.com.semillero.semillatronalfa.repositories.queries;

import ar.com.semillero.semillatronalfa.models.filters.ProjectFilter;
import ar.com.semillero.semillatronalfa.utils.NullUtils;

public class ProjectQueries {

    public static String findProjectList() {
        StringBuilder query = new StringBuilder();
        query.append("select p.* from project p inner join project_details pd on  p.id = pd.project_id")
                .append(" inner join management_team mt on p.id = mt.project_id where p.is_active = 1")
                .append(" order by p.timestamp desc");
        return query.toString();
    }

    public static String projectFilter(ProjectFilter projectFilter) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT p.* FROM project p")
                .append(" INNER JOIN project_details pd ON p.id = pd.project_id")
                .append(" INNER JOIN management_team mt ON p.id = mt.project_id")
                .append(" INNER JOIN company_data cd ON p.id = cd.project_id")
                .append(" WHERE p.is_active = 1");
        if (!NullUtils.nullOrEmpty(projectFilter.getSearchBar())) {
            query.append(" AND (p.project_name LIKE '%").append(projectFilter.getSearchBar()).append("%'")
                    .append(" OR mt.product_owner_full_name LIKE '%").append(projectFilter.getSearchBar()).append("%'")
                    .append(" OR mt.project_manager_full_name LIKE '%").append(projectFilter.getSearchBar()).append("%'")
                    .append(" OR mt.team_leader_full_name LIKE '%").append(projectFilter.getSearchBar()).append("%'")
                    .append(" OR cd.ally LIKE '%").append(projectFilter.getSearchBar()).append("%')");
        }
        if (!NullUtils.nullOrEmpty(projectFilter.getProjectType()) && projectFilter.getProjectType().length > 1) {
            query.append(QueryMethods.queryIterator(projectFilter.getProjectType(), " AND pd.project_type"));
        }
        if (!NullUtils.nullOrEmpty(projectFilter.getProjectStatus())) {
            query.append(QueryMethods.queryIterator(projectFilter.getProjectStatus(), " AND pd.project_status"));
        }
        if (!NullUtils.nullOrEmpty(projectFilter.getProjectStage()) && projectFilter.getProjectStage().length > 1) {
            query.append(QueryMethods.queryIterator(projectFilter.getProjectStage(), " AND pd.project_stage"));
        }
        if (!NullUtils.nullOrEmpty(projectFilter.getInitialDateFrom())) {
            query.append(" AND pd.initial_date >= '").append(projectFilter.getInitialDateFrom()).append("'");
        }
        if (!NullUtils.nullOrEmpty(projectFilter.getInitialDateTo())) {
            query.append(" AND pd.initial_date <= '").append(projectFilter.getInitialDateTo()).append("'");
        }
        if (!NullUtils.nullOrEmpty(projectFilter.getEndingDateFrom())) {
            query.append(" AND pd.ending_date >= '").append(projectFilter.getEndingDateFrom()).append("'");
        }
        if (!NullUtils.nullOrEmpty(projectFilter.getEndingDateTo())) {
            query.append(" AND pd.ending_date <= '").append(projectFilter.getEndingDateTo()).append("'");
        }
        if (!NullUtils.nullOrEmpty(projectFilter.getCommission())) {
            query.append(QueryMethods.queryIterator(projectFilter.getCommission(), " AND p.commission"));
        }
        if (!NullUtils.nullOrEmpty(projectFilter.getTimestampFrom())) {
            query.append(" AND p.timestamp >= '").append(projectFilter.getTimestampFrom()).append("'");
        }
        if (!NullUtils.nullOrEmpty(projectFilter.getTimestampTo())) {
            query.append(" AND p.timestamp <= '").append(projectFilter.getTimestampTo()).append("'");
        }

        query.append(" ORDER BY p.timestamp DESC;");
        System.out.println("---------------------QUERY---------------------");
        System.out.println(query);
        return query.toString();
    }

    public static String listOfProjectCommissions() {
        StringBuilder query = new StringBuilder();
        query.append("SELECT distinct commission FROM project p")
                .append(" WHERE p.is_active = 1")
                .append(" ORDER BY p.commission ASC;");
        return query.toString();
    }
}
