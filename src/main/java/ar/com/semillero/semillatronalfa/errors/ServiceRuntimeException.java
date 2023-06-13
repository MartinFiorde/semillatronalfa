package ar.com.semillero.semillatronalfa.errors;

public class ServiceRuntimeException extends RuntimeException {

    public ServiceRuntimeException(String msn) {
        super(msn);
    }
}