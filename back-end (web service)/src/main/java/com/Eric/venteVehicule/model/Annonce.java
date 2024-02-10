package com.Eric.venteVehicule.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "annonce")
public class Annonce {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_annonce")
    private int idAnnonce;
    @Column(name = "description_")
    private String description;
    private String marque;
    private String modele;
    private double prix;
    @Column(name = "status_")
    private int status = 0;
    @Column(name = "date_annonce")
    private LocalDateTime dateAnnonce = LocalDateTime.now();
    @ManyToOne
    @JoinColumn(name = "id_utilisateur")
    private Utilisateur utilisateur;
}