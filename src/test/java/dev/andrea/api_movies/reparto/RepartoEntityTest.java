package dev.andrea.api_movies.reparto;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class RepartoEntityTest {

    @Test
    void testRepartoEntity_Initialization() {
        RepartoEntity reparto = new RepartoEntity();
        assertThat(reparto, is(instanceOf(RepartoEntity.class)));
    }

    @Test
    void testRepartoEntity_SettersAndGetters() {
        RepartoEntity reparto = new RepartoEntity();
        reparto.setNombre("Marta Sanchez");
        reparto.setSexo("Mujer");
        reparto.setFechaNacimiento(LocalDate.of(1992, 11, 4));

        assertThat(reparto.getNombre(), is(equalTo("Marta Sanchez")));
        assertThat(reparto.getSexo(), is(equalTo("Mujer")));
        assertThat(reparto.getFechaNacimiento(), is(equalTo(LocalDate.of(1992, 11, 4))));
    }
}