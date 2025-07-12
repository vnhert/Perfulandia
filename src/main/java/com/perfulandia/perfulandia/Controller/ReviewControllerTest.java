package com.perfulandia.perfulandia.Controller;

import com.perfulandia.perfulandia.Model.Review;
import com.perfulandia.perfulandia.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@WebMvcTest(ReviewController.class)
public class ReviewControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean private ReviewService reviewService;

    @Test
    void testGetAllReviews() throws Exception {
        Review review = new Review();
        review.setId(1);
        review.setComentario("Buen producto");
        when(reviewService.findAll()).thenReturn(List.of(review));

        mockMvc.perform(get("/reviews"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].comentario").value("Buen producto"));
    }
}
}
