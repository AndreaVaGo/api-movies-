package dev.andrea.api_movies.pelicula;

import dev.andrea.api_movies.anio.AnioEntity;
import dev.andrea.api_movies.anio.AnioRepository;
import dev.andrea.api_movies.anio.exceptions.AnioNotFoundException;
import dev.andrea.api_movies.genero.GeneroEntity;
import dev.andrea.api_movies.genero.GeneroRepository;
import dev.andrea.api_movies.genero.exceptions.GeneroNotFoundException;
import dev.andrea.api_movies.pelicula.dtos.PeliculaDTORequest;
import dev.andrea.api_movies.pelicula.dtos.PeliculaDTOResponse;
import dev.andrea.api_movies.pelicula.exceptions.PeliculaNotFoundException;
import dev.andrea.api_movies.pelicula.mappers.PeliculaMapper;
import dev.andrea.api_movies.reparto.RepartoEntity;
import dev.andrea.api_movies.reparto.RepartoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.LinkedHashSet;
import java.util.ArrayList;
import java.util.Set;

@Service
public class PeliculaServiceImpl implements PeliculaService {

    private final PeliculaRepository repository;
    private final GeneroRepository generoRepository;
    private final AnioRepository anioRepository;
    private final RepartoRepository repartoRepository;

    public PeliculaServiceImpl(PeliculaRepository repository, GeneroRepository generoRepository,
            AnioRepository anioRepository, RepartoRepository repartoRepository) {
        this.repository = repository;
        this.generoRepository = generoRepository;
        this.anioRepository = anioRepository;
        this.repartoRepository = repartoRepository;
    }

    @Override
    public List<PeliculaDTOResponse> getEntities() {
        return repository.findAll().stream()
                .map(PeliculaMapper::toDTO)
                .toList();
    }

    @Override
    public PeliculaDTOResponse getById(Long id) {
        PeliculaEntity pelicula = repository.findById(id)
                .orElseThrow(() -> new PeliculaNotFoundException("Pelicula not found. Id " + id + " does not exist."));
        return PeliculaMapper.toDTO(pelicula);
    }

    @Override
    public PeliculaDTOResponse storeEntity(PeliculaDTORequest dto) {
        GeneroEntity genero = generoRepository.findById(dto.generoId())
                .orElseThrow(() -> new GeneroNotFoundException("Genero not found. Id " + dto.generoId() + " does not exist."));
        AnioEntity anio = anioRepository.findById(dto.anioId())
                .orElseThrow(() -> new AnioNotFoundException("Anio not found. Id " + dto.anioId() + " does not exist."));

        List<RepartoEntity> reparto = new ArrayList<>();
        if (dto.repartoIds() != null) {
            reparto = repartoRepository.findAllById(dto.repartoIds());
        }

        PeliculaEntity pelicula = PeliculaMapper.toEntity(dto, genero, anio, reparto);
        PeliculaEntity peliculaGuardada = repository.save(pelicula);
        return PeliculaMapper.toDTO(peliculaGuardada);
    }

    @Override
    public PeliculaDTOResponse update(Long id, PeliculaDTORequest dto) {
        PeliculaEntity existente = repository.findById(id)
                .orElseThrow(() -> new PeliculaNotFoundException("Pelicula not found. Id " + id + " does not exist."));

        GeneroEntity genero = generoRepository.findById(dto.generoId())
                .orElseThrow(() -> new GeneroNotFoundException("Genero not found. Id " + dto.generoId() + " does not exist."));
        AnioEntity anio = anioRepository.findById(dto.anioId())
                .orElseThrow(() -> new AnioNotFoundException("Anio not found. Id " + dto.anioId() + " does not exist."));

        List<RepartoEntity> reparto = new ArrayList<>();
        if (dto.repartoIds() != null) {
            reparto = repartoRepository.findAllById(dto.repartoIds());
        }

        existente.setTitulo(dto.titulo());
        existente.setCalificacion(dto.calificacion());
        existente.setDuracion(dto.duracion());
        existente.setGenero(genero);
        existente.setAnio(anio);
        existente.setReparto(reparto);

        PeliculaEntity actualizada = repository.save(existente);
        return PeliculaMapper.toDTO(actualizada);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<PeliculaDTOResponse> findByTituloOrGenero(String texto) {
        List<PeliculaEntity> porTitulo = repository.findByTituloContainingIgnoreCase(texto);
        List<PeliculaEntity> porGenero = repository.findByGenero_NombreContainingIgnoreCase(texto);

        Set<PeliculaEntity> resultado = new LinkedHashSet<>();
        resultado.addAll(porTitulo);
        resultado.addAll(porGenero);

        return resultado.stream()
                .map(PeliculaMapper::toDTO)
                .toList();
    }
    
}