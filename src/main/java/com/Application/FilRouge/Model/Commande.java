package com.Application.FilRouge.Model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime datedecommande;
    private double montant_total;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private LivraisonType livraisonType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDatedecommande(LocalDateTime datedecommande) {
        return this.datedecommande;
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

    public Commande(Long id, LocalDateTime datedecommande, double montant_total, Status status, LivraisonType livraisonType) {
        this.id = id;
        this.datedecommande = datedecommande;
        this.montant_total = montant_total;
        this.status = status;
        this.livraisonType = livraisonType;
    }

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @OneToMany(mappedBy = "commande")
    private List<Plats> plats;

    public List<Plats> getPlats() {
        return plats;
    }

    public void setPlats(List<Plats> plats) {
        this.plats = plats;
    }

    public LocalDateTime getDatedecommande() {
        return datedecommande;
    }
}
