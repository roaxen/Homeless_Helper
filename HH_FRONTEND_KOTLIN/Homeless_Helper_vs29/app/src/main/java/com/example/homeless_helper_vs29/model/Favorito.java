package com.example.homeless_helper_vs29.model;


public class Favorito {

    private FavoritoPK id;

    @Override
    public String toString() {
        return "Favorito [id=" + id + "]";
    }

    public Favorito() {
    }

    public Favorito(String email, int idLugar) {

        this.id = new FavoritoPK(email, idLugar);
    }
    public FavoritoPK getId() {
        return this.id;
    }

    public void setId(FavoritoPK id) {
        this.id = id;
    }
}