package dev.andrea.api_movies.genero;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

public class GeneroEntityTest {

    @Test
    void testGeneroEntity_Initialization() {
        GeneroEntity genero = new GeneroEntity();
        assertThat(genero, is(instanceOf(GeneroEntity.class)));
    }

    @Test
    void testGeneroEntity_SettersAndGetters() {
        GeneroEntity genero = new GeneroEntity();
        genero.setNombre("Terror");

        assertThat(genero.getNombre(), is(equalTo("Terror")));
    }
}