package com.perfulandia.perfulandia.Controller;


import com.perfulandia.perfulandia.Model.Review;
import com.perfulandia.perfulandia.Model.ReviewActionRequest;
import com.perfulandia.perfulandia.Model.User;
import com.perfulandia.perfulandia.Repository.ReviewRepository;
import com.perfulandia.perfulandia.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
@Tag(name = "Reseñas", description = "Operaciones relacionadas con las reseñas")
@RestController
@RequestMapping("/reseñas")
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ReviewService reviewService;

    @Operation(summary = "Agregar una nueva reseña")
    @PostMapping
    public String guardarReseña(@RequestBody ReviewActionRequest request) {
        return reviewService.guardarReseña(request.getSolicitante(), request.getReview());
    }


}
