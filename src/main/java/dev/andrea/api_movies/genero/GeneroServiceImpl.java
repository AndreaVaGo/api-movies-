package dev.andrea.api_movies.genero;

import dev.andrea.api_movies.genero.dtos.GeneroDTORequest;
import dev.andrea.api_movies.genero.dtos.GeneroDTOResponse;
import dev.andrea.api_movies.genero.mappers.GeneroMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import dev.andrea.api_movies.genero.exceptions.GeneroNotFoundException;

@Service
public class GeneroServiceImpl implements GeneroService {

    private final GeneroRepository repository;

    public GeneroServiceImpl(GeneroRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<GeneroDTOResponse> getEntities() {
        return repository.findAll().stream()
                .map(GeneroMapper::toDTO)
                .toList();
    }

    @Override
    public GeneroDTOResponse getById(Long id) {
        GeneroEntity genero = repository.findById(id)
                .orElseThrow(() -> new GeneroNotFoundException("Genero not found. Id " + id + " does not exist."));
        return GeneroMapper.toDTO(genero);
    }

    @Override
    public GeneroDTOResponse storeEntity(GeneroDTORequest dto) {
        GeneroEntity genero = GeneroMapper.toEntity(dto);
        GeneroEntity generoGuardado = repository.save(genero);
        return GeneroMapper.toDTO(generoGuardado);
    }
}