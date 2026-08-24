package dev.andrea.api_movies.pelicula;

import dev.andrea.api_movies.exceptions.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Pelicula not found")
public class PeliculaNotFoundException extends ApiException {

    public PeliculaNotFoundException(String message) {
        super(message);
    }
}