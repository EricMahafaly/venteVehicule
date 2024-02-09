package com.Eric.venteVehicule.connex;

import com.Eric.venteVehicule.model.Annonce;
import com.Eric.venteVehicule.model.Utilisateur;
import com.Eric.venteVehicule.service.UtilisateurService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Request {
    private ConnexPostgres connexPostgres = new ConnexPostgres();
    private UtilisateurService utilisateurService;

    public void ajoutAnnonceFavoris(Annonce annonce, Utilisateur utilisateur) {
        Connection connection = this.connexPostgres.getConnex();
        try {
            String sql = String.format("INSERT INTO favoris VALUES (DEFAULT, %d, %d)", annonce.getIdAnnonce(), utilisateur.getId());
            Statement statement = connection.createStatement();
            statement.execute(sql);
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Annonce> findAllFavoris(Utilisateur utilisateur) {
        List<Annonce> allAnnonce = new ArrayList<>();
        Connection connection = this.connexPostgres.getConnex();
        try {
            String sql = String.format("SELECT * FROM v_favoris WHERE id_utilisateur_proprietaire = %d", utilisateur.getId());
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(sql);
            while(res.next()) {
                Annonce annonce = new Annonce();
                Utilisateur utilisateur2 = new Utilisateur();
                annonce.setIdAnnonce(res.getInt("id_annonce"));
                annonce.setDescription(res.getString("description_"));
                annonce.setMarque(res.getString("marque"));
                annonce.setModele(res.getString("modele"));
                annonce.setPrix(res.getDouble("prix"));
                annonce.setStatus(res.getInt("status_"));
                annonce.setDateAnnonce(res.getTimestamp("date_annonce").toLocalDateTime());
                utilisateur2.setId(res.getInt("id_utilisateur"));
                utilisateur2.setNomUtilisateur(res.getString("nom_utilisateur"));
                annonce.setUtilisateur(utilisateur2);
                allAnnonce.add(annonce);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return allAnnonce;
    }

    // faire des recherche
    public List<Annonce> researchByDate(String date) {
        List<Annonce> allAnnonce = new ArrayList<>();
        Connection connection = this.connexPostgres.getConnex();
        try {
            String sql = String.format("SELECT * FROM v_annonce WHERE date_annonce >= '%s 00:00:00' AND date_annonce <= '%s 23:59:59'", date, date);
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(sql);
            while(res.next()) {
                Annonce annonce = new Annonce();
                Utilisateur utilisateur = new Utilisateur();
                annonce.setIdAnnonce(res.getInt("id_annonce"));
                annonce.setDescription(res.getString("description_"));
                annonce.setMarque(res.getString("marque"));
                annonce.setModele(res.getString("modele"));
                annonce.setPrix(res.getDouble("prix"));
                annonce.setStatus(res.getInt("status_"));
                annonce.setDateAnnonce(res.getTimestamp("date_annonce").toLocalDateTime());
                utilisateur.setId(res.getInt("id_utilisateur"));
                utilisateur.setNomUtilisateur(res.getString("nom_utilisateur"));
                annonce.setUtilisateur(utilisateur);
                allAnnonce.add(annonce);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return allAnnonce;
    }

    public List<Annonce> researchByPrix(double prix) {
        List<Annonce> allAnnonce = new ArrayList<>();
        Connection connection = this.connexPostgres.getConnex();
        try {
            String sql = String.format("SELECT * FROM v_annonce WHERE prix = %.2f", prix);
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(sql);
            while(res.next()) {
                Annonce annonce = new Annonce();
                Utilisateur utilisateur = new Utilisateur();
                annonce.setIdAnnonce(res.getInt("id_annonce"));
                annonce.setDescription(res.getString("description_"));
                annonce.setMarque(res.getString("marque"));
                annonce.setModele(res.getString("modele"));
                annonce.setPrix(res.getDouble("prix"));
                annonce.setStatus(res.getInt("status_"));
                annonce.setDateAnnonce(res.getTimestamp("date_annonce").toLocalDateTime());
                utilisateur.setId(res.getInt("id_utilisateur"));
                utilisateur.setNomUtilisateur(res.getString("nom_utilisateur"));
                annonce.setUtilisateur(utilisateur);
                allAnnonce.add(annonce);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return allAnnonce;
    }

    public List<Annonce> researchByMarque(String marque) {
        List<Annonce> allAnnonce = new ArrayList<>();
        Connection connection = this.connexPostgres.getConnex();
        try {
            String sql = String.format("SELECT * FROM v_annonce WHERE marque LIKE '%%%s%%'", marque);
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(sql);
            while(res.next()) {
                Annonce annonce = new Annonce();
                Utilisateur utilisateur = new Utilisateur();
                annonce.setIdAnnonce(res.getInt("id_annonce"));
                annonce.setDescription(res.getString("description_"));
                annonce.setMarque(res.getString("marque"));
                annonce.setModele(res.getString("modele"));
                annonce.setPrix(res.getDouble("prix"));
                annonce.setStatus(res.getInt("status_"));
                annonce.setDateAnnonce(res.getTimestamp("date_annonce").toLocalDateTime());
                utilisateur.setId(res.getInt("id_utilisateur"));
                utilisateur.setNomUtilisateur(res.getString("nom_utilisateur"));
                annonce.setUtilisateur(utilisateur);
                allAnnonce.add(annonce);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return allAnnonce;
    }

    public List<Annonce> researchByModele(String modele) {
        List<Annonce> allAnnonce = new ArrayList<>();
        Connection connection = this.connexPostgres.getConnex();
        try {
            String sql = String.format("SELECT * FROM v_annonce WHERE modele LIKE '%%%s%%'", modele);
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(sql);
            while(res.next()) {
                Annonce annonce = new Annonce();
                Utilisateur utilisateur = new Utilisateur();
                annonce.setIdAnnonce(res.getInt("id_annonce"));
                annonce.setDescription(res.getString("description_"));
                annonce.setMarque(res.getString("marque"));
                annonce.setModele(res.getString("modele"));
                annonce.setPrix(res.getDouble("prix"));
                annonce.setStatus(res.getInt("status_"));
                annonce.setDateAnnonce(res.getTimestamp("date_annonce").toLocalDateTime());
                utilisateur.setId(res.getInt("id_utilisateur"));
                utilisateur.setNomUtilisateur(res.getString("nom_utilisateur"));
                annonce.setUtilisateur(utilisateur);
                allAnnonce.add(annonce);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return allAnnonce;
    }

    public int getLastIdAnnonce() {
        int value =0;
        Connection connection = this.connexPostgres.getConnex();
        try {
            String sql = "select max(id_annonce) from annonce";
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(sql);
            while(res.next()) {
                value = res.getInt(1);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return value;
    }

    public void insertPhotos(int idAnnonce, String pathFile) {
        Connection connection = this.connexPostgres.getConnex();
        try {
            String sql = String.format("INSERT INTO photo_annonce VALUES (DEFAULT, '%s', %d)", pathFile, idAnnonce);
            Statement statement = connection.createStatement();
            statement.execute(sql);
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}