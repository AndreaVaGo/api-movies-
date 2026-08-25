package dev.andrea.api_movies.reparto;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import dev.andrea.api_movies.reparto.dtos.RepartoDTOResponse;

@WebMvcTest(controllers = RepartoController.class)
public class RepartoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RepartoService service;

    @Test
    void testIndex_ShouldReturnRepartos() throws Exception {
        RepartoDTOResponse dto = new RepartoDTOResponse(1L, "Marta Sanchez", "Mujer", LocalDate.of(1992, 11, 4));
        List<RepartoDTOResponse> repartos = List.of(dto);

        when(service.getEntities()).thenReturn(repartos);

        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/repartos"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertThat(response.getStatus(), is(equalTo(200)));
        assertThat(response.getContentAsString(), containsString("Marta Sanchez"));
    }

    @Test
    void testGetById_ShouldReturnReparto() throws Exception {
        RepartoDTOResponse dto = new RepartoDTOResponse(1L, "Marta Sanchez", "Mujer", LocalDate.of(1992, 11, 4));

        when(service.getById(1L)).thenReturn(dto);

        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/repartos/1"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertThat(response.getStatus(), is(equalTo(200)));
        assertThat(response.getContentAsString(), containsString("Marta Sanchez"));
    }
}