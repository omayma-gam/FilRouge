package com.Application.FilRouge.Model;

import java.time.LocalDateTime;

public class Commande {
    private Long id;
    private LocalDateTime datedecommande;
    private double montant_total;
    private Status status;
    private LivraisonType livraisonType;

}
