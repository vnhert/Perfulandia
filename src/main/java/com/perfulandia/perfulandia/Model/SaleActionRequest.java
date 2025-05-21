package com.perfulandia.perfulandia.Model;
import lombok.Data;

@Data
public class SaleActionRequest {
    private User solicitante;
    private Sale sale;
}
