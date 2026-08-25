package dev.andrea.api_movies.genero;

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

import dev.andrea.api_movies.genero.dtos.GeneroDTOResponse;

@WebMvcTest(controllers = GeneroController.class)
public class GeneroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private GeneroService service;

    @Test
    void testIndex_ShouldReturnGeneros() throws Exception {
        GeneroDTOResponse dto = new GeneroDTOResponse(1L, "Terror");
        List<GeneroDTOResponse> generos = List.of(dto);

        when(service.getEntities()).thenReturn(generos);

        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/generos"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertThat(response.getStatus(), is(equalTo(200)));
        assertThat(response.getContentAsString(), containsString("Terror"));
    }

    @Test
    void testGetById_ShouldReturnGenero() throws Exception {
        GeneroDTOResponse dto = new GeneroDTOResponse(1L, "Terror");

        when(service.getById(1L)).thenReturn(dto);

        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/generos/1"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertThat(response.getStatus(), is(equalTo(200)));
        assertThat(response.getContentAsString(), containsString("Terror"));
    }
}