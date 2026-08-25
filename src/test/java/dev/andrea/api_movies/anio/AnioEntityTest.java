package dev.andrea.api_movies.anio;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

public class AnioEntityTest {

    @Test
    void testAnioEntity_Initialization() {
        AnioEntity anio = new AnioEntity();
        assertThat(anio, is(instanceOf(AnioEntity.class)));
    }

    @Test
    void testAnioEntity_SettersAndGetters() {
        AnioEntity anio = new AnioEntity();
        anio.setAnio(2020);

        assertThat(anio.getAnio(), is(equalTo(2020)));
    }
}