package ar.com.semillero.semillatronalfa.errors;

public class ErrorService extends Exception {

    public ErrorService(String msn) {
        super(msn);
    }
}