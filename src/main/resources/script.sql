\c postgres
DROP DATABASE vente_vehicule;

CREATE DATABASE vente_vehicule;
\c vente_vehicule

CREATE TABLE utilisateur (
    id_utilisateur SERIAL PRIMARY KEY,
    nom_utilisateur VARCHAR(255),
    mots_de_passe VARCHAR(255),
    actif BOOLEAN,
    role_ VARCHAR(255)
);

CREATE TABLE jwt (
    id SERIAL PRIMARY KEY,
    valeur VARCHAR(255),
    desactive BOOLEAN,
    expire BOOLEAN,
    id_utilisateur INTEGER,
    FOREIGN KEY (id_utilisateur) REFERENCES utilisateur(id_utilisateur)
);

CREATE TABLE annonce (
    id_annonce SERIAL PRIMARY KEY,
    description_ TEXT,
    marque VARCHAR(255),
    modele VARCHAR(255),
    prix DOUBLE PRECISION,
    status_ INTEGER,
    date_annonce TIMESTAMP,
    id_utilisateur INTEGER,
    FOREIGN KEY (id_utilisateur) REFERENCES utilisateur(id_utilisateur)
);

CREATE TABLE favoris (
    id_favoris SERIAL PRIMARY KEY,
    id_annonce INTEGER,
    id_utilisateur_proprietaire INTEGER,
    FOREIGN KEY (id_annonce) REFERENCES annonce(id_annonce),
    FOREIGN KEY (id_utilisateur_proprietaire) REFERENCES utilisateur(id_utilisateur)
);

CREATE TABLE photo_annonce (
    id_photo_annonce SERIAL PRIMARY KEY,
    chemin VARCHAR(255),
    id_annonce INTEGER,
    FOREIGN KEY (id_annonce) REFERENCES annonce(id_annonce)
);

CREATE TABLE voiture (
    id_voiture SERIAL PRIMARY KEY,
    marque VARCHAR(255),
    categorie VARCHAR(255),
    modele VARCHAR(255)
);


-- VIEW --


CREATE OR REPLACE VIEW v_annonce AS
    SELECT annonce.id_annonce, annonce.description_, annonce.marque, annonce.modele, annonce.prix, annonce.status_, annonce.date_annonce,
    utilisateur.id_utilisateur, utilisateur.nom_utilisateur, utilisateur.mots_de_passe, utilisateur.actif, utilisateur.role_
    FROM annonce JOIN utilisateur ON annonce.id_utilisateur = utilisateur.id_utilisateur
;

CREATE OR REPLACE VIEW v_favoris AS 
    SELECT annonce.id_annonce, annonce.description_, annonce.marque, annonce.modele, annonce.prix, annonce.status_, annonce.date_annonce,
    utilisateur.id_utilisateur, utilisateur.nom_utilisateur, utilisateur.mots_de_passe, utilisateur.actif,
    utilisateur.role_, favoris.id_utilisateur_proprietaire
    FROM favoris JOIN annonce ON favoris.id_annonce = annonce.id_annonce
    JOIN utilisateur ON annonce.id_utilisateur = utilisateur.id_utilisateur
;