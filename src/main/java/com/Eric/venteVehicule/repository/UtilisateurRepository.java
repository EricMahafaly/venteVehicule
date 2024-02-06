package com.Eric.venteVehicule.repository;


import com.Eric.venteVehicule.model.Utilisateur;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UtilisateurRepository extends CrudRepository<Utilisateur, Integer> {
    Optional<Utilisateur> findByNomUtilisateur(String nomUtilisateur);

}
