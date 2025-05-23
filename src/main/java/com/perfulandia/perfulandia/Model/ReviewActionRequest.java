package com.perfulandia.perfulandia.Model;

import lombok.Data;

@Data
public class ReviewActionRequest {
    private User solicitante;
    private Review review;
}
