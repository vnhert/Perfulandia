package com.perfulandia.perfulandia.Model;

import lombok.Data;

@Data
public class OrderActionRequest {
    private User solicitante;
    private Order order;
}
