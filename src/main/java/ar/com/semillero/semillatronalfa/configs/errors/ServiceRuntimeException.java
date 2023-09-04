package ar.com.semillero.semillatronalfa.configs.errors;

public class ServiceRuntimeException extends RuntimeException {

    public ServiceRuntimeException(String msn) {
        super(msn);
    }
}