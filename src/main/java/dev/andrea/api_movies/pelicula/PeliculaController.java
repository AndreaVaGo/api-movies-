package dev.andrea.api_movies.pelicula;

import dev.andrea.api_movies.pelicula.dtos.PeliculaDTORequest;
import dev.andrea.api_movies.pelicula.dtos.PeliculaDTOResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController

@RequestMapping(path = "${api-endpoint}/peliculas")
public class PeliculaController {

    private final PeliculaService service;

    public PeliculaController(PeliculaService service) {
        this.service = service;
    }

    @GetMapping("")
    public List<PeliculaDTOResponse> index() {
        return service.getEntities();
    }

    @GetMapping("{id}")
    public PeliculaDTOResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }


    @PostMapping("")
    public ResponseEntity<PeliculaDTOResponse> add(@Valid @RequestBody PeliculaDTORequest dto) {
        PeliculaDTOResponse dtoResponse = service.storeEntity(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoResponse);
    }

    @PutMapping("{id}")
    public PeliculaDTOResponse update(@PathVariable Long id, @Valid @RequestBody PeliculaDTORequest dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }


    @GetMapping("buscar")
    public List<PeliculaDTOResponse> buscar(@RequestParam String texto) {
        return service.findByTituloOrGenero(texto);
    }
}