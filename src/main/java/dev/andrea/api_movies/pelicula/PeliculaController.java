package dev.andrea.api_movies.pelicula;

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
    public List<PeliculaEntity> index() {
        return service.getEntities();
    }

    @GetMapping("{id}")
    public PeliculaEntity getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping("")
    public PeliculaEntity add(@RequestBody PeliculaEntity pelicula) {
        return service.add(pelicula);
    }

    @PutMapping("{id}")
    public PeliculaEntity update(@PathVariable Long id, @RequestBody PeliculaEntity pelicula) {
        return service.update(id, pelicula);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("buscar")
    public List<PeliculaEntity> buscar(@RequestParam String texto) {
        return service.findByTituloOrGenero(texto);
    }
}