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

/*esta clase recibe peticiones HTTP y responde JSON*/
@RestController
/*prefijo de ruta: todas las peticiones de esta clase empiezan por /api/v1/peliculas*/
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

    /*añade una película nueva. @RequestBody coge el JSON que envía el cliente (ej. {"titulo": "Matrix", "duracion": 136, ...}) 
    y lo convierte automáticamente en un objeto PeliculaEntity. Se lo pasamos al servicio, que lo guarda con repository.save() */
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

    /*@RequestParam captura lo que va después del ? en la UR*/
    @GetMapping("buscar")
    public List<PeliculaEntity> buscar(@RequestParam String texto) {
        return service.findByTituloOrGenero(texto);
    }
}