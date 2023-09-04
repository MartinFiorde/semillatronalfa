package ar.com.semillero.semillatronalfa.utils;

import java.time.LocalDate;

public final class NullUtils {

    public static boolean nullOrEmpty(String string) {
    return (string == null ? true : ((string.equals("") || string.trim().equals(""))));
    }
    public static boolean nullOrEmpty(String[] string) {
    return (string == null ? true : (string.length == 0));
    }
    public static boolean nullOrEmpty(LocalDate date) {
    return (date == null ? true : (date.getYear()< 2020));
    }
}
