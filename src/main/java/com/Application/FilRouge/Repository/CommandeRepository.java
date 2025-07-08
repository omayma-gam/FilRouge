package com.Application.FilRouge.Repository;

import com.Application.FilRouge.Model.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeRepository extends JpaRepository<Commande,Long> {
}
