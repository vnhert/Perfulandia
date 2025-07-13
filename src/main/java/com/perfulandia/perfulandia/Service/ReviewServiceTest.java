package com.perfulandia.perfulandia.Service;

import com.perfulandia.perfulandia.Model.Review;
import com.perfulandia.perfulandia.Model.User;
import com.perfulandia.perfulandia.Repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Autowired
private ReviewRepository reviewRepository;

public String guardarReseña(User solicitante, Review review) {
    if (!solicitante.puedeDejarReseñas()) {
        return "No tienes permiso para dejar reseñas";
    }
    reviewRepository.save(review);
    return "Reseña guardada con éxito";
}

public class ReviewServiceTest {
    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private ReviewService reviewService;

    @Test
    void testGuardarReseñaConPermiso() {
        User solicitante = new User();
        solicitante.setNombre("admin");
        solicitante.setPuedeDejarReseñas(true);

        Review review = new Review();
        review.setComentario("Excelente servicio");

        when(reviewRepository.save(review)).thenReturn(review);

        String result = reviewService.guardarReseña(solicitante, review);

        assertEquals("Reseña guardada con éxito", result);
        verify(reviewRepository, times(1)).save(review);
    }

    @Test
    void testGuardarReseñaSinPermiso() {
        User solicitante = new User();
        solicitante.setNombre("usuario");
        solicitante.setPuedeDejarReseñas(false);

        Review review = new Review();
        review.setComentario("Buen producto");

        String result = reviewService.guardarReseña(solicitante, review);

        assertEquals("No tienes permiso para dejar reseñas", result);
        verify(reviewRepository, never()).save(any(Review.class));
    }
}
