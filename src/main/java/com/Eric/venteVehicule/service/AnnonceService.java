package com.Eric.venteVehicule.service;

import com.Eric.venteVehicule.connex.Request;
import com.Eric.venteVehicule.model.Annonce;
import com.Eric.venteVehicule.model.Utilisateur;
import com.Eric.venteVehicule.repository.AnnonceRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@Service
public class AnnonceService {
    private final AnnonceRepository annonceRepository;
    private FileService fileService;

    public void creeAnnonce(Annonce annonce, MultipartFile[] file) {
        Utilisateur utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        annonce.setUtilisateur(utilisateur);
        this.annonceRepository.save(annonce);
        int idAnnonce = new Request().getLastIdAnnonce();
        String url;
        for(int i = 0; i< file.length; i++) {
            url = this.fileService.upload(file[i]);
            new Request().insertPhotos(idAnnonce, url);
        }
    }

    public List<Annonce> findAll() {
        return this.annonceRepository.findAll();
    }

    public List<Annonce> historique() {
        Utilisateur utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return this.annonceRepository.findAllByUtilisateur(utilisateur);
    }

    public void ajoutFavoris(int idAnnonce) {
        Utilisateur utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Annonce annonce = this.annonceRepository.findById(idAnnonce).orElseThrow(() -> new RuntimeException("annonce introve"));
        new Request().ajoutAnnonceFavoris(annonce, utilisateur);
    }

    public List<Annonce> findAllFavoris() {
        Utilisateur utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new Request().findAllFavoris(utilisateur);
    }

    public List<Annonce> researchByDate(String date) {
        return new Request().researchByDate(date);
    }

    public List<Annonce> researchByPrix(double prix) {
        return new Request().researchByPrix(prix);
    }

    public List<Annonce> researchByMarque(String marque) {
        return new Request().researchByMarque(marque);
    }

    public List<Annonce> researchByModele(String modele) {
        return new Request().researchByModele(modele);
    }
}