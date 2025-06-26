package com.Application.FilRouge.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Client extends User{

    @OneToMany(mappedBy = "client")
   private List<Commande> commandes;

    public List<Commande> getCommandes() {
        return commandes;
    }

    public void setCommandes(List<Commande> commandes) {
        this.commandes = commandes;
    }
}
