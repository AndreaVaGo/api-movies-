package dev.andrea.api_movies.anio;

import dev.andrea.api_movies.implementations.InterfaceGenericService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping(path = "${api-endpoint}/anios")
public class AnioController {

    private final InterfaceGenericService<AnioEntity> service;

    public AnioController(InterfaceGenericService<AnioEntity> service) {
        this.service = service;
    }

    @GetMapping("")
    public List<AnioEntity> index() {
        return service.getEntities();
    }

    @GetMapping("{id}")
    public AnioEntity getById(@PathVariable Long id) {
        return service.getById(id);
    }
}