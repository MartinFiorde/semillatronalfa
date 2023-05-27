package ar.com.semillero.semillatronalfa.errors;

public class ServiceException extends Exception {

    public ServiceException(String msn) {
        super(msn);
    }
}