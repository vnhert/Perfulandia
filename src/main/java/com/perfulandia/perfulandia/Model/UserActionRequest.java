package com.perfulandia.perfulandia.Model;

public class UserActionRequest {
    private User solicitante;
    private User user;

    public UserActionRequest() {
    }
    public User getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(User solicitante) {
        this.solicitante = solicitante;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
