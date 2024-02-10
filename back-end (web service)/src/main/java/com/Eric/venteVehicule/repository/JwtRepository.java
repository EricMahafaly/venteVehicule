package com.Eric.venteVehicule.repository;


import com.Eric.venteVehicule.model.Jwt;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface JwtRepository extends CrudRepository<Jwt, Integer> {
    //Optional<Jwt> findByValeurAndDesactiverAndExpire(String valeur, boolean desactiver, boolean expire);

    /*
    @Query("FROM Jwt j WHERE j.expire = :expire AND j.desactive = :desactive AND j.utilisateur.nomUtilisateur = :nomUtilisateur")
    Optional<Jwt> findUtilisateurValidToken(String nomUtilisateur, boolean desactiver, boolean expire);

    @Query("FROM Jwt j WHERE j.utilisateur.nomUtilisateur = :nomUtilisateur")
    Stream<Jwt> findUtilisateur(String nomUtilisateur);


     */
    Optional<Jwt> findByValeur(String valeur);
}
