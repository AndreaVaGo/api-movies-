package dev.andrea.api_movies.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/*esta clase vigila todos los controladores del proyecto, y si alguno lanza una excepción, pasa por aquí antes de responder al cliente*/
@RestControllerAdvice
public class GlobalExceptionHandler {

    /*cuando se lance una excepción de tipo ApiException (o cualquiera de sus hijas, como PeliculaNotFoundException), ejecuta este método*/
    @ExceptionHandler(ApiException.class)
    /*el método devuelve una respuesta HTTP cuyo cuerpo es un Map (que Spring convertirá automáticamente a JSON)*/
    public ResponseEntity<Map<String, Object>> handleApiException(ApiException ex) {
        /*Por defecto, asumimos el código 500 (error genérico del servidor), por si la excepción no trae información de qué código usar*/
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        ResponseStatus responseStatus = ex.getClass().getAnnotation(ResponseStatus.class);
        if (responseStatus != null) {
            status = responseStatus.code();
        }

        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", ex.getMessage());

        return ResponseEntity.status(status).body(body);
    }

    /*cuando falla una validación @Valid (por ejemplo, @NotBlank en el titulo), Spring lanza esta excepción automáticamente*/
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }
}