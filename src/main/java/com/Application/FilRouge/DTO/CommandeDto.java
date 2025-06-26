package com.Application.FilRouge.DTO;

import com.Application.FilRouge.Model.Commande;
import com.Application.FilRouge.Model.LivraisonType;
import com.Application.FilRouge.Model.Status;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;



public class CommandeDto implements Serializable {
    Long id;
    LocalDateTime datedecommande;
    double montant_total;
    Status status;
    LivraisonType livraisonType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDatedecommande() {
        return datedecommande;
    }

    public void setDatedecommande(LocalDateTime datedecommande) {
        this.datedecommande = datedecommande;
    }

    public double getMontant_total() {
        return montant_total;
    }

    public void setMontant_total(double montant_total) {
        this.montant_total = montant_total;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LivraisonType getLivraisonType() {
        return livraisonType;
    }

    public void setLivraisonType(LivraisonType livraisonType) {
        this.livraisonType = livraisonType;
    }
}