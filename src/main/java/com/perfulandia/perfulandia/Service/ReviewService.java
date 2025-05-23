package com.perfulandia.perfulandia.Service;

import com.perfulandia.perfulandia.Model.Review;
import com.perfulandia.perfulandia.Model.User;
import com.perfulandia.perfulandia.Repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public String guardarReseña(User solicitante, Review review) {
        if (!solicitante.puedeDejarReseñas()) {
            return "No tienes permiso para dejar reseñas";
        }
        reviewRepository.save(review);
        return "Reseña guardada con éxito";
    }


}
