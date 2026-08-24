package dev.andrea.api_movies.reparto;

import dev.andrea.api_movies.implementations.InterfaceGenericService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping(path = "${api-endpoint}/repartos")
public class RepartoController {

    private final InterfaceGenericService<RepartoEntity> service;

    public RepartoController(InterfaceGenericService<RepartoEntity> service) {
        this.service = service;
    }

    @GetMapping("")
    public List<RepartoEntity> index() {
        return service.getEntities();
    }

    @GetMapping("{id}")
    public RepartoEntity getById(@PathVariable Long id) {
        return service.getById(id);
    }
}