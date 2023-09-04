package ar.com.semillero.semillatronalfa.repositories.queries;

import ar.com.semillero.semillatronalfa.models.filters.EventFilter;
import ar.com.semillero.semillatronalfa.repositories.queries.QueryMethods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EventQueries {

    // FUNCIONALIDAD PARA LA BÚSQUEDA DE EVENTOS MEDIANTE LA BARRA DE BÚSQUEDA
    public static String searchBarQuery(EventFilter eventFilter) {
        StringBuilder query = new StringBuilder();
        query.append("select event.* from event inner join event_details on event.id = event_details.event_id ")
                .append(" where event.is_active = 1 and ")
                .append("(event_details.instructor like '%"+ eventFilter.getSearchBar() +"%' ")
                .append("or event_details.location like '%"+ eventFilter.getSearchBar() +"%' ")
                .append("or event_details.description like '%"+ eventFilter.getSearchBar() +"%' ")
                .append("or event.organized_by like '%"+ eventFilter.getSearchBar() +"%' ")
                .append("or event.title like '%"+ eventFilter.getSearchBar() +"%') ")
                .append("order by event.date desc");
        return query.toString();
    }

    // FUNCIONALIDAD PARA FILTRO DE EVENTOS
    public static String filterEvent(EventFilter eventFilter) {
        StringBuilder query = new StringBuilder();
        query.append("select event.* from event inner join event_details on event.id = event_details.event_id ");


        if (eventFilter.getModality().length > 0  && eventFilter.getModality() != null) {
            String column = " and event_details.modality";
            query.append(QueryMethods.queryIterator(eventFilter.getModality(), column));
        }
        if (eventFilter.getOrigin().length > 0 && eventFilter.getOrigin() != null) {
            String column = " and event_details.origin";
            query.append(QueryMethods.queryIterator(eventFilter.getOrigin(), column));
        }
        if (eventFilter.getDestination().length > 0 && eventFilter.getDestination() != null) {
            String column = " and event_details.destination";
            query.append(QueryMethods.queryIterator(eventFilter.getDestination(), column));
        }
        if (eventFilter.getDuration() != null) {
            query.append(" and event_details.duration >= " + eventFilter.getDuration());
        }

        query.append(" where event.is_active = 1");

        if(eventFilter.getFromDate() != null && eventFilter.getUntilDate() != null) {
            String column = "event.date";
            query.append(" and ").append(QueryMethods.dateBetween(eventFilter.getFromDate(), eventFilter.getUntilDate(), column));
        }

        if (eventFilter.getOfferedBySemillero().length > 0 && eventFilter.getOfferedBySemillero() != null) {
            String column = " and event.offered_by_semillero ";
            query.append(QueryMethods.queryIterator(QueryMethods.booleanListToArrayString(eventFilter.getOfferedBySemillero()),
                    column));
        }
        if (eventFilter.getTotalAttendance() != null) {
            query.append(" and event.total_attendance >= " + eventFilter.getTotalAttendance());
        }
        if (eventFilter.getType().length > 0 && eventFilter.getType() != null) {
            String column = " and event.type";
            query.append(QueryMethods.queryIterator(eventFilter.getType(), column));
        }
        if (eventFilter.getApproach().length > 0 && eventFilter.getApproach() != null) {
            String column = " and event.approach";
            query.append(QueryMethods.queryIterator(eventFilter.getApproach(), column));
        }
        if (eventFilter.getStatus().length > 0 && eventFilter.getStatus() != null) {
            String column = " and event.status";
            query.append(QueryMethods.queryIterator(eventFilter.getStatus(), column));
        }
        query.append(" order by event.date desc");
        System.out.println(query);
        return query.toString();
    }
}
