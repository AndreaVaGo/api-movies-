package dev.andrea.api_movies.anio;

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

import dev.andrea.api_movies.anio.dtos.AnioDTORequest;
import dev.andrea.api_movies.anio.dtos.AnioDTOResponse;

@ExtendWith(MockitoExtension.class)
public class AnioServiceImplTest {

    private AnioServiceImpl service;

    @Mock
    private AnioRepository repository;

    @BeforeEach
    void setUp() {
        service = new AnioServiceImpl(repository);
    }

    @Test
    void testGetEntities() {
        AnioEntity anio = new AnioEntity();
        anio.setAnio(2020);

        when(repository.findAll()).thenReturn(List.of(anio));

        List<AnioDTOResponse> anios = service.getEntities();

        assertThat(anios.size(), is(equalTo(1)));
        assertThat(anios.get(0).anio(), is(equalTo(2020)));
    }

    @Test
    void testGetById() {
        AnioEntity anio = new AnioEntity();
        anio.setAnio(2021);

        when(repository.findById(1L)).thenReturn(Optional.of(anio));

        AnioDTOResponse resultado = service.getById(1L);

        assertThat(resultado.anio(), is(equalTo(2021)));
    }

    @Test
    void testStoreEntity() {
        AnioDTORequest dto = new AnioDTORequest(2022);

        AnioEntity anioGuardado = new AnioEntity();
        anioGuardado.setAnio(2022);

        when(repository.save(any(AnioEntity.class))).thenReturn(anioGuardado);

        AnioDTOResponse resultado = service.storeEntity(dto);

        assertThat(resultado.anio(), is(equalTo(2022)));
    }
}