package dev.andrea.api_movies.reparto;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.andrea.api_movies.reparto.dtos.RepartoDTORequest;
import dev.andrea.api_movies.reparto.dtos.RepartoDTOResponse;

@ExtendWith(MockitoExtension.class)
public class RepartoServiceImplTest {

    private RepartoServiceImpl service;

    @Mock
    private RepartoRepository repository;

    @BeforeEach
    void setUp() {
        service = new RepartoServiceImpl(repository);
    }

    @Test
    void testGetEntities() {
        RepartoEntity reparto = new RepartoEntity();
        reparto.setNombre("Marta Sanchez");

        when(repository.findAll()).thenReturn(List.of(reparto));

        List<RepartoDTOResponse> repartos = service.getEntities();

        assertThat(repartos.size(), is(equalTo(1)));
        assertThat(repartos.get(0).nombre(), is(equalTo("Marta Sanchez")));
    }

    @Test
    void testGetById() {
        RepartoEntity reparto = new RepartoEntity();
        reparto.setNombre("David Torres");

        when(repository.findById(1L)).thenReturn(Optional.of(reparto));

        RepartoDTOResponse resultado = service.getById(1L);

        assertThat(resultado.nombre(), is(equalTo("David Torres")));
    }

    @Test
    void testStoreEntity() {
        RepartoDTORequest dto = new RepartoDTORequest("Elena Castro", "Mujer", LocalDate.of(1995, 9, 30));

        RepartoEntity repartoGuardado = new RepartoEntity();
        repartoGuardado.setNombre("Elena Castro");

        when(repository.save(any(RepartoEntity.class))).thenReturn(repartoGuardado);

        RepartoDTOResponse resultado = service.storeEntity(dto);

        assertThat(resultado.nombre(), is(equalTo("Elena Castro")));
    }
}