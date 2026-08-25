package dev.andrea.api_movies.anio;

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

import dev.andrea.api_movies.anio.dtos.AnioDTOResponse;

@WebMvcTest(controllers = AnioController.class)
public class AnioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AnioService service;

    @Test
    void testIndex_ShouldReturnAnios() throws Exception {
        AnioDTOResponse dto = new AnioDTOResponse(1L, 2020);
        List<AnioDTOResponse> anios = List.of(dto);

        when(service.getEntities()).thenReturn(anios);

        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/anios"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertThat(response.getStatus(), is(equalTo(200)));
        assertThat(response.getContentAsString(), containsString("2020"));
    }

    @Test
    void testGetById_ShouldReturnAnio() throws Exception {
        AnioDTOResponse dto = new AnioDTOResponse(1L, 2020);

        when(service.getById(1L)).thenReturn(dto);

        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/anios/1"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertThat(response.getStatus(), is(equalTo(200)));
        assertThat(response.getContentAsString(), containsString("2020"));
    }
}