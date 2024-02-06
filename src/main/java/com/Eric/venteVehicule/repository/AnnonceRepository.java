package com.Eric.venteVehicule.repository;

import com.Eric.venteVehicule.model.Annonce;
import com.Eric.venteVehicule.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AnnonceRepository extends JpaRepository<Annonce, Integer> {
    List<Annonce> findAllByUtilisateur(Utilisateur utilisateur);
}
