package dev.andrea.api_movies.reparto;

import dev.andrea.api_movies.reparto.dtos.RepartoDTORequest;
import dev.andrea.api_movies.reparto.dtos.RepartoDTOResponse;
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
@RequestMapping(path = "${api-endpoint}/repartos")
public class RepartoController {

    private final RepartoService service;

    public RepartoController(RepartoService service) {
        this.service = service;
    }

    @GetMapping("")
    public List<RepartoDTOResponse> index() {
        return service.getEntities();
    }

    @GetMapping("{id}")
    public RepartoDTOResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping("")
    public ResponseEntity<RepartoDTOResponse> add(@Valid @RequestBody RepartoDTORequest dto) {
        RepartoDTOResponse dtoResponse = service.storeEntity(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoResponse);
    }
}