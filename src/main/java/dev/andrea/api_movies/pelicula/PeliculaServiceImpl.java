package dev.andrea.api_movies.pelicula;

import org.springframework.stereotype.Service;

import dev.andrea.api_movies.pelicula.exceptions.PeliculaNotFoundException;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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
        return repository.findById(id)
                .orElseThrow(() -> new PeliculaNotFoundException("Pelicula not found. Id " + id + " does not exist."));
    }

    @Override
    public PeliculaEntity add(PeliculaEntity pelicula) {
        return repository.save(pelicula);
    }

    @Override
    public PeliculaEntity update(Long id, PeliculaEntity pelicula) {
        PeliculaEntity existente = repository.findById(id)
                .orElseThrow(() -> new PeliculaNotFoundException("Pelicula not found. Id " + id + " does not exist."));
        existente.setTitulo(pelicula.getTitulo());
        existente.setCalificacion(pelicula.getCalificacion());
        existente.setDuracion(pelicula.getDuracion());
        existente.setGenero(pelicula.getGenero());
        existente.setAnio(pelicula.getAnio());
        existente.setReparto(pelicula.getReparto());
        return repository.save(existente);
    }

    @Override
    public List<PeliculaEntity> findByTituloOrGenero(String texto) {
        List<PeliculaEntity> porTitulo = repository.findByTituloContainingIgnoreCase(texto);
        List<PeliculaEntity> porGenero = repository.findByGenero_NombreContainingIgnoreCase(texto);

        Set<PeliculaEntity> resultado = new LinkedHashSet<>();
        resultado.addAll(porTitulo);
        resultado.addAll(porGenero);

        return new ArrayList<>(resultado);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

}