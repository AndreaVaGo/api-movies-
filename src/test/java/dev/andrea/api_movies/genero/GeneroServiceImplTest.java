package dev.andrea.api_movies.genero;

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

import dev.andrea.api_movies.genero.dtos.GeneroDTORequest;
import dev.andrea.api_movies.genero.dtos.GeneroDTOResponse;

@ExtendWith(MockitoExtension.class)
public class GeneroServiceImplTest {

    private GeneroServiceImpl service;

    @Mock
    private GeneroRepository repository;

    @BeforeEach
    void setUp() {
        service = new GeneroServiceImpl(repository);
    }

    @Test
    void testGetEntities() {
        GeneroEntity genero = new GeneroEntity();
        genero.setNombre("Terror");

        when(repository.findAll()).thenReturn(List.of(genero));

        List<GeneroDTOResponse> generos = service.getEntities();

        assertThat(generos.size(), is(equalTo(1)));
        assertThat(generos.get(0).nombre(), is(equalTo("Terror")));
    }

    @Test
    void testGetById() {
        GeneroEntity genero = new GeneroEntity();
        genero.setNombre("Comedia");

        when(repository.findById(1L)).thenReturn(Optional.of(genero));

        GeneroDTOResponse resultado = service.getById(1L);

        assertThat(resultado.nombre(), is(equalTo("Comedia")));
    }

    @Test
    void testStoreEntity() {
        GeneroDTORequest dto = new GeneroDTORequest("Drama");

        GeneroEntity generoGuardado = new GeneroEntity();
        generoGuardado.setNombre("Drama");

        when(repository.save(any(GeneroEntity.class))).thenReturn(generoGuardado);

        GeneroDTOResponse resultado = service.storeEntity(dto);

        assertThat(resultado.nombre(), is(equalTo("Drama")));
    }
}