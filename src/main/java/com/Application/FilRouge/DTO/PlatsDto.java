package com.Application.FilRouge.DTO;

import com.Application.FilRouge.Model.Category;

import java.io.Serializable;

public class PlatsDto implements Serializable {
    private Long id;
    private String name;
    private String description;
    private double prix;
    private boolean available;
    private Category category;
    private CommandeDto commande;
    private RestaurantDto restaurant;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public CommandeDto getCommande() {
        return commande;
    }

    public void setCommande(CommandeDto commande) {
        this.commande = commande;
    }

    public RestaurantDto getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantDto restaurant) {
        this.restaurant = restaurant;
    }
}
