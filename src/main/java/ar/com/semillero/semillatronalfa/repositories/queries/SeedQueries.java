package ar.com.semillero.semillatronalfa.repositories.queries;

import java.util.Optional;

import ar.com.semillero.semillatronalfa.models.filters.SeedFilter;
import ar.com.semillero.semillatronalfa.repositories.queries.QueryMethods;
import org.springframework.stereotype.Component;

@Component
public class SeedQueries {

    public String orderedSeedList(Optional<String> statusF, Optional<String> commissionO, Optional<String> lastNameO) {
        String query = "SELECT seed.* FROM seed "
                + "inner join seed_contact_data on seed.id = seed_contact_data.seed_id_id "
                + "inner join seed_follow_up on seed.id = seed_follow_up.seed_id_id "
                + "inner join seed_personal_data on seed.id = seed_personal_data.seed_id_id "
                + "inner join seed_postulation_data on seed.id = seed_postulation_data.seed_id_id "
                + "inner join seed_status on seed_follow_up.id = seed_status.seed_follow_up_id_id "
                + "where seed.is_active = 1 ";
        if (statusF.orElse("Activa").equals("Activa")) {
            query = query.concat("and seed_status.status_primary = 'Activa' ");
        }
        query = query.concat("order by seed_follow_up.commission " + commissionO.orElseGet(() -> "asc") + ", seed_personal_data.last_name " + lastNameO.orElseGet(() -> "asc"));
        return query;
    }
    
    public String getSeedByDni(Integer dni) {
        String query = "SELECT seed.* FROM seed inner join seed_personal_data on seed.id = seed_personal_data.seed_id_id where seed_personal_data.dni = " + dni;
        return query;
    }

    public static String seachBarSeed(SeedFilter seedFilter, Optional<String> commissionO, Optional<String> lastNameO) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT seed.* FROM seed ")
                .append("inner join seed_contact_data on seed.id = seed_contact_data.seed_id_id ")
                .append("inner join seed_follow_up on seed.id = seed_follow_up.seed_id_id ")
                .append("inner join seed_personal_data on seed.id = seed_personal_data.seed_id_id ")
                .append("inner join seed_postulation_data on seed.id = seed_postulation_data.seed_id_id ")
                .append("inner join seed_status on seed_follow_up.id = seed_status.seed_follow_up_id_id ")
                .append("and (seed_personal_data.first_name like '%").append(seedFilter.getSearchBar()).append("%'")
                .append(" or seed_personal_data.last_name like '%").append(seedFilter.getSearchBar()).append("%')")
                .append(" where seed.is_active = 1").append(" order by seed_follow_up.commission ")
                .append(commissionO.orElseGet(() -> "asc")).append(", seed_personal_data.last_name ")
                .append(lastNameO.orElseGet(() -> "asc"));
        return query.toString();
    }
    public static String seachBarSeed2(SeedFilter seedFilter) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT seed.* FROM seed ")
                .append("inner join seed_contact_data on seed.id = seed_contact_data.seed_id_id ")
                .append("inner join seed_follow_up on seed.id = seed_follow_up.seed_id_id ")
                .append("inner join seed_personal_data on seed.id = seed_personal_data.seed_id_id ")
                .append("inner join seed_postulation_data on seed.id = seed_postulation_data.seed_id_id ")
                .append("inner join seed_status on seed_follow_up.id = seed_status.seed_follow_up_id_id ")
                .append("and (seed_personal_data.first_name like '%").append(seedFilter.getSearchBar()).append("%'")
                .append(" or seed_personal_data.last_name like '%").append(seedFilter.getSearchBar()).append("%')")
                .append(" where seed.is_active = 1").append(" order by seed_follow_up.commission asc")
                .append(", seed_personal_data.last_name asc");
        return query.toString();
    }

    // TODO: FILTRO A DESARROLLAR
    public static String filterSeed(SeedFilter seedFilter) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT seed.* FROM seed ")
                .append("inner join seed_contact_data on seed.id = seed_contact_data.seed_id_id ")
                .append("inner join seed_follow_up on seed.id = seed_follow_up.seed_id_id ")
                .append("inner join seed_personal_data on seed.id = seed_personal_data.seed_id_id ")
                .append("inner join seed_postulation_data on seed.id = seed_postulation_data.seed_id_id ")
                .append("inner join seed_status on seed_follow_up.id = seed_status.seed_follow_up_id_id ");
        //conditions for filter selection for users
        if (seedFilter.getCountry() != null && seedFilter.getCountry().length > 0) {
            String column = " and seed_personal_data.country";
            query.append(QueryMethods.queryIterator(seedFilter.getCountry(), column));
        }
        if (seedFilter.getCommission() != null && seedFilter.getCommission().length > 0) {
            String column = " and seed_follow_up.commission";
            query.append(QueryMethods.queryIterator(seedFilter.getCommission(), column));
        }
        if (seedFilter.getCertificationString() != null && seedFilter.getCertificationString().length > 0) {
            String column = " and seed_follow_up.certification_string";
            query.append(QueryMethods.queryIterator(seedFilter.getCertificationString(), column));
        }
        if (seedFilter.getRol() != null && seedFilter.getRol().length > 0) {
            String column = " and seed_postulation_data.rol";
            query.append(QueryMethods.queryIterator(seedFilter.getRol(), column));
        }
        if (seedFilter.getGender() != null && seedFilter.getGender().length > 0) {
            String column = " and seed_personal_data.gender";
            query.append(QueryMethods.queryIterator(seedFilter.getGender(), column));
        }
        if (seedFilter.getTurn() != null && seedFilter.getTurn().length > 0) {
            String column = " and seed_postulation_data.turn";
            query.append(QueryMethods.queryIterator(seedFilter.getTurn(), column));
        }
        if (seedFilter.getStatusSecundary().length == 0) {
            System.out.println("entro aca para el status");
            if (seedFilter.getStatus() != null && seedFilter.getStatus().length > 0) {
               String column = " and seed_status.status_primary";
                query.append(QueryMethods.queryIterator(seedFilter.getStatus(), column)); 
            }
            
        }
        if (seedFilter.getStatusSecundary() != null && seedFilter.getStatusSecundary().length > 0) {
            if (seedFilter.getStatus() != null && seedFilter.getStatus().length > 0) {
                String column2 = " where seed_status.status_primary";
                query.append(QueryMethods.queryIterator(seedFilter.getStatus(), column2)); 
                String column = " or seed_status.status_secondary";
                query.append(QueryMethods.queryIterator(seedFilter.getStatusSecundary(), column));
                System.out.println("aca esta la query que combina todo");
                System.out.println(query.toString());
            }else{
            String column = " and seed_status.status_secondary";
            query.append(QueryMethods.queryIterator(seedFilter.getStatusSecundary(), column));
            }
        }   
        if (seedFilter.getAssignedProject() != null && seedFilter.getAssignedProject().length > 0) {
            String column = " and seed_status.project_name";
            query.append(QueryMethods.queryIterator(seedFilter.getAssignedProject(), column));
        }
        if (seedFilter.getFromDate() != null && seedFilter.getUntilDate() != null) {
            String column = "seed_follow_up.postulation_date";
            query.append(" and ").append(QueryMethods.dateBetween(seedFilter.getFromDate().toLocalDate(), seedFilter.getUntilDate().toLocalDate(), column));
        }
        if (seedFilter.getRecommendationString() != null && seedFilter.getRecommendationString().length > 0) {
            String column = " and seed_follow_up.recommendation_string";
            query.append(QueryMethods.queryIterator(seedFilter.getRecommendationString(), column));
        }
        if (seedFilter.getStatus().length >0) {
            if (seedFilter.getStatusSecundary().length == 0) {
                query.append(" where seed.is_active = 1");
            }
            if (seedFilter.getStatusSecundary().length > 0) {
                query.append(" and seed.is_active = 1");
            }
        }
        if (seedFilter.getStatus().length == 0) {
            if (seedFilter.getStatusSecundary().length == 0) {
                query.append(" where seed.is_active = 1");
            }
            if (seedFilter.getStatusSecundary().length > 0) {
                query.append(" where seed.is_active = 1");
            }
        }
        //query.append(" where seed.is_active = 1")
                query.append(" order by seed_follow_up.commission asc")
                .append(", seed_personal_data.last_name asc");
        return query.toString();
    }
    //LISTA DE PAISES PARA SELECCION DE LOS MISMOS
    public String listOfCountry(){
        StringBuilder query = new StringBuilder();
        query.append("SELECT distinct country FROM seed_personal_data ")
                .append("inner join seed on seed.id = seed_personal_data.seed_id_id ")
                .append("where seed.is_active = 1 ")
                .append("order by seed_personal_data.country asc");
        return query.toString();
    }
    //LIST OF THE PROJECTS ACTIVE
    public String ListOfProjectActive(){
        StringBuilder query = new StringBuilder();
        query.append("SELECT distinct project_name FROM project ")
                .append("where project.is_active = 1 ")
                .append("order by project.project_name asc");
        return query.toString();
    }
    //LIST OF THE COMMISSION OF SEED ACTIVE
    public String listCommissionSeedActive(){
        StringBuilder query = new StringBuilder();
        query.append("SELECT distinct commission FROM seed_follow_up ")
                .append("inner join seed on seed.id = seed_follow_up.seed_id_id ")
                .append("where seed.is_active = 1 ")
                .append("order by seed_follow_up.commission asc");
        return query.toString();
    }
}
