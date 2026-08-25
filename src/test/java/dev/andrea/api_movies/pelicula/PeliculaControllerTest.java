package dev.andrea.api_movies.pelicula;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import dev.andrea.api_movies.pelicula.dtos.PeliculaDTOResponse;

@WebMvcTest(controllers = PeliculaController.class)
public class PeliculaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PeliculaService service;

    @Test
    void testIndex_ShouldReturnPeliculas() throws Exception {
        PeliculaDTOResponse dto = new PeliculaDTOResponse(1L, "Matrix", 16, 136, null, null, null);
        List<PeliculaDTOResponse> peliculas = List.of(dto);

        when(service.getEntities()).thenReturn(peliculas);

        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/peliculas"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertThat(response.getStatus(), is(equalTo(200)));
        assertThat(response.getContentAsString(), containsString("Matrix"));
    }

    @Test
    void testGetById_ShouldReturnPelicula() throws Exception {
        PeliculaDTOResponse dto = new PeliculaDTOResponse(1L, "Matrix", 16, 136, null, null, null);

        when(service.getById(1L)).thenReturn(dto);

        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/peliculas/1"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertThat(response.getStatus(), is(equalTo(200)));
        assertThat(response.getContentAsString(), containsString("Matrix"));
    }
}