package dev.andrea.api_movies.reparto.exceptions;

import dev.andrea.api_movies.exceptions.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Reparto not found")
public class RepartoNotFoundException extends ApiException {
    public RepartoNotFoundException(String message) {
        super(message);
    }
}