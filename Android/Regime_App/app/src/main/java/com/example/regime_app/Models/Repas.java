package com.example.regime_app.Models;

public class Repas {
    private Plat entree;
    private Plat plat;
    private Plat dessert;
    private String pathImage;
    public Repas( Plat entree, Plat plat, Plat dessert, String pathImage) {
        this.entree = entree;
        this.plat = plat;
        this.dessert = dessert;

    }

    public Plat getDessert() {
        return dessert;
    }

    public Plat getEntree() {
        return entree;
    }

    public Plat getPlat() {
        return plat;
    }

    public String getPathImage() {
        return pathImage;
    }
}
