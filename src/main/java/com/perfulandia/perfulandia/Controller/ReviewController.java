package com.perfulandia.perfulandia.Controller;


import com.perfulandia.perfulandia.Model.Review;
import com.perfulandia.perfulandia.Model.ReviewActionRequest;
import com.perfulandia.perfulandia.Model.User;
import com.perfulandia.perfulandia.Repository.ReviewRepository;
import com.perfulandia.perfulandia.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reseñas")
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ReviewService reviewService;


    @PostMapping
    public String addProduct(@RequestBody ReviewActionRequest request) {
        return reviewService.guardarReseña(request.getSolicitante(), request.getReview());
    }


}
