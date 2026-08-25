package dev.andrea.api_movies.pelicula;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.andrea.api_movies.anio.AnioEntity;
import dev.andrea.api_movies.anio.AnioRepository;
import dev.andrea.api_movies.genero.GeneroEntity;
import dev.andrea.api_movies.genero.GeneroRepository;
import dev.andrea.api_movies.pelicula.dtos.PeliculaDTORequest;
import dev.andrea.api_movies.pelicula.dtos.PeliculaDTOResponse;
import dev.andrea.api_movies.reparto.RepartoRepository;

@ExtendWith(MockitoExtension.class)
public class PeliculaServiceImplTest {

    private PeliculaServiceImpl service;

    @Mock
    private PeliculaRepository repository;
    @Mock
    private GeneroRepository generoRepository;
    @Mock
    private AnioRepository anioRepository;
    @Mock
    private RepartoRepository repartoRepository;

    @BeforeEach
    void setUp() {
        service = new PeliculaServiceImpl(repository, generoRepository, anioRepository, repartoRepository);
    }

    @Test
    void testGetEntities() {
        GeneroEntity genero = new GeneroEntity();
        genero.setNombre("Terror");
        AnioEntity anio = new AnioEntity();
        anio.setAnio(2020);

        PeliculaEntity pelicula = new PeliculaEntity();
        pelicula.setTitulo("Noche sin fin");
        pelicula.setGenero(genero);
        pelicula.setAnio(anio);

        when(repository.findAll()).thenReturn(List.of(pelicula));

        List<PeliculaDTOResponse> peliculas = service.getEntities();

        assertThat(peliculas.size(), is(equalTo(1)));
        assertThat(peliculas.get(0).titulo(), is(equalTo("Noche sin fin")));
    }

    @Test
    void testGetById() {
        GeneroEntity genero = new GeneroEntity();
        AnioEntity anio = new AnioEntity();
        PeliculaEntity pelicula = new PeliculaEntity();
        pelicula.setTitulo("Viaje estelar");
        pelicula.setGenero(genero);
        pelicula.setAnio(anio);

        when(repository.findById(1L)).thenReturn(Optional.of(pelicula));

        PeliculaDTOResponse resultado = service.getById(1L);

        assertThat(resultado.titulo(), is(equalTo("Viaje estelar")));
    }

    @Test
    void testStoreEntity() {
        PeliculaDTORequest dto = new PeliculaDTORequest("Matrix", 16, 136, 1L, 1L, null);

        GeneroEntity genero = new GeneroEntity();
        AnioEntity anio = new AnioEntity();

        when(generoRepository.findById(1L)).thenReturn(Optional.of(genero));
        when(anioRepository.findById(1L)).thenReturn(Optional.of(anio));

        PeliculaEntity peliculaGuardada = new PeliculaEntity();
        peliculaGuardada.setTitulo("Matrix");

        when(repository.save(any(PeliculaEntity.class))).thenReturn(peliculaGuardada);

        PeliculaDTOResponse resultado = service.storeEntity(dto);

        assertThat(resultado.titulo(), is(equalTo("Matrix")));
    }
}