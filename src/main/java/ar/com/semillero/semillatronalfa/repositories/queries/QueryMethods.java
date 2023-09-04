package ar.com.semillero.semillatronalfa.repositories.queries;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class QueryMethods {

    // MÉTODO PARA FILTRAR POR CAMPO DE SELECCIÓN MÚLTIPLE.
    public static String queryIterator(String[] list, String column) {
        Iterator<String> mod = Arrays.stream(list).iterator();
        StringBuilder query = new StringBuilder();
        query.append(column + " in (");
        while(mod.hasNext()) {
            query.append("'" + mod.next() + "'");
            query.append(mod.hasNext() ? ", " : ")");
        }
        return query.toString();
    }

    // CONVIERTE ARRAY DE BOOLEANOS A ARRAY DE STRINGS
    public static String[] booleanListToArrayString(Boolean[] list) {
        String[] booleanArray = new String[list.length];
        List<String> booleans = new ArrayList<>();
        for ( Boolean bool : list ) {
            booleans.add(bool ? "1" : "0");
        }
        for (int i = 0; i < booleanArray.length; i++) {
            booleanArray[i] = booleans.get(i);
        }
        System.out.println(booleanArray);
        return booleanArray;
    }

    // FILTRAR FECHA POR MES Y AÑO
    public static String dateBetweenMonthYear(int fromMonth, int fromYear, int untilMonth, int untilYear, String column) {
        StringBuilder query = new StringBuilder();
        query.append("(month(").append(column).append(") >= ").append(fromMonth)
                .append(" and ")
                .append("year(").append(column).append(") >= ").append(fromYear).append(")")
                .append(" and ")
                .append("(month(").append(column).append(") <= ").append(untilMonth)
                .append(" and ")
                .append("year(").append(column).append(") <= ").append(untilYear).append(")")
        ;
        return query.toString();
    }

    // FILTRAR POR FECHA
    public static String dateBetween(LocalDate fromDate, LocalDate untilDate, String column) {
        StringBuilder query = new StringBuilder();
        if(untilDate.isAfter(fromDate) || untilDate.isEqual(fromDate)) {
            query.append("(").append(column).append(" >= '").append(fromDate)
                    .append("' and ")
                    .append(column).append(" <= '").append(untilDate).append("')");
        }
        System.out.println(query);
        return query.toString();
    }


}
