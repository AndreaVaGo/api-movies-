package dev.andrea.api_movies.genero;

import dev.andrea.api_movies.genero.dtos.GeneroDTORequest;
import dev.andrea.api_movies.genero.dtos.GeneroDTOResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping(path = "${api-endpoint}/generos")
public class GeneroController {

    private final GeneroService service;

    public GeneroController(GeneroService service) {
        this.service = service;
    }

    @GetMapping("")
    public List<GeneroDTOResponse> index() {
        return service.getEntities();
    }

    @GetMapping("{id}")
    public GeneroDTOResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping("")
    public ResponseEntity<GeneroDTOResponse> add(@Valid @RequestBody GeneroDTORequest dto) {
        GeneroDTOResponse dtoResponse = service.storeEntity(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoResponse);
    }
}