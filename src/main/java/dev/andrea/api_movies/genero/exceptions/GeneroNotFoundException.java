package dev.andrea.api_movies.genero.exceptions;

import dev.andrea.api_movies.exceptions.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Genero not found")
public class GeneroNotFoundException extends ApiException {
    
    public GeneroNotFoundException(String message) {
        super(message);
    }
}