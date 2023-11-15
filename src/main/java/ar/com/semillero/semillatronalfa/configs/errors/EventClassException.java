package ar.com.semillero.semillatronalfa.configs.errors;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class EventClassException extends Exception {

    private final HttpStatus httpStatus;

    public EventClassException(String message) {
        super(message);
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public EventClassException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
