package com.perfulandia.perfulandia.Controller;

import com.perfulandia.perfulandia.Model.Review;
import com.perfulandia.perfulandia.Model.ReviewActionRequest;
import com.perfulandia.perfulandia.Model.User;
import com.perfulandia.perfulandia.Service.ReviewService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@WebMvcTest(ReviewController.class)
public class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReviewService reviewService;

    @Test
    void testGuardarReseña() throws Exception {
        // Configuración del mock
        when(reviewService.guardarReseña(any(User.class), any(Review.class)))
                .thenReturn("Reseña creada con éxito");

        // Ejecución y validación
        mockMvc.perform(post("/reseñas")
                        .contentType(APPLICATION_JSON)
                        .content("{\"solicitante\":{\"nombre\":\"admin\"},\"review\":{\"comentario\":\"Excelente producto\"}}"))
                .andExpect(status().isCreated())
                .andExpect(content().string("Reseña creada con éxito"));
    }

    @Test
    void testGuardarReseñaError() throws Exception {
        // Configuración del mock para un caso de error
        when(reviewService.guardarReseña(any(User.class), any(Review.class)))
                .thenReturn("Error al guardar la reseña");

        // Ejecución y validación
        mockMvc.perform(post("/reseñas")
                        .contentType(APPLICATION_JSON)
                        .content("{\"solicitante\":{\"nombre\":\"admin\"},\"review\":{\"comentario\":\"\"}}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Error al guardar la reseña"));
    }
}

