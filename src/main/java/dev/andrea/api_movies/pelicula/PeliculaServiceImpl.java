package dev.andrea.api_movies.pelicula;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PeliculaServiceImpl implements PeliculaService {

    private final PeliculaRepository repository;

    public PeliculaServiceImpl(PeliculaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<PeliculaEntity> getEntities() {
        return repository.findAll();
    }

    @Override
    public PeliculaEntity getById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public PeliculaEntity add(PeliculaEntity pelicula) {
        return repository.save(pelicula);
    }

    @Override
    public PeliculaEntity update(Long id, PeliculaEntity pelicula) {
        PeliculaEntity existente = repository.findById(id).orElseThrow();
        existente.setTitulo(pelicula.getTitulo());
        existente.setCalificacion(pelicula.getCalificacion());
        existente.setDuracion(pelicula.getDuracion());
        existente.setGenero(pelicula.getGenero());
        existente.setAnio(pelicula.getAnio());
        existente.setReparto(pelicula.getReparto());
        return repository.save(existente);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<PeliculaEntity> findByTituloOrGenero(String texto) {
        return repository.findAll();
    }
}