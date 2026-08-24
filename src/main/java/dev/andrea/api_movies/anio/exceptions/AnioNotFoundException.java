package dev.andrea.api_movies.anio.exceptions;

import dev.andrea.api_movies.exceptions.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Anio not found")
public class AnioNotFoundException extends ApiException {
    
    public AnioNotFoundException(String message) {
        super(message);
    }
}