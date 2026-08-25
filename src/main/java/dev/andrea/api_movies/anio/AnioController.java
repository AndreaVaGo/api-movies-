package dev.andrea.api_movies.anio;

import dev.andrea.api_movies.anio.dtos.AnioDTORequest;
import dev.andrea.api_movies.anio.dtos.AnioDTOResponse;
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
@RequestMapping(path = "${api-endpoint}/anios")
public class AnioController {

    private final AnioService service;

    public AnioController(AnioService service) {
        this.service = service;
    }

    @GetMapping("")
    public List<AnioDTOResponse> index() {
        return service.getEntities();
    }

    @GetMapping("{id}")
    public AnioDTOResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping("")
    public ResponseEntity<AnioDTOResponse> add(@Valid @RequestBody AnioDTORequest dto) {
        AnioDTOResponse dtoResponse = service.storeEntity(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoResponse);
    }
}