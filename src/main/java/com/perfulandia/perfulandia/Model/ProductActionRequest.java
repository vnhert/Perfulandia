package com.perfulandia.perfulandia.Model;

public class ProductActionRequest {
    private User solicitante;
    private Product product;

    public User getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(User solicitante) {
        this.solicitante = solicitante;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
