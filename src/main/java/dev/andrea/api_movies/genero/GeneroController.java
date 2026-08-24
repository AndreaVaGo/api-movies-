package dev.andrea.api_movies.genero;

import dev.andrea.api_movies.implementations.InterfaceGenericService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/*le dice a Spring "esta clase recibe peticiones HTTP y devuelve datos (JSON*/
@RestController
@RequestMapping(path = "${api-endpoint}/generos")
public class GeneroController {

    private final InterfaceGenericService<GeneroEntity> service;

    public GeneroController(InterfaceGenericService<GeneroEntity> service) {
        this.service = service;
    }

    @GetMapping("")
    public List<GeneroEntity> index() {
        return service.getEntities();
    }

    @GetMapping("{id}")
    public GeneroEntity getById(@PathVariable Long id) {
        return service.getById(id);
    }
}