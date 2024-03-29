package com.Eric.venteVehicule.controller;

import com.Eric.venteVehicule.model.Annonce;
import com.Eric.venteVehicule.model.Img;
import com.Eric.venteVehicule.service.AnnonceService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.MediaType.*;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(path = "annonce")
@CrossOrigin(origins = "http://localhost:3000")
public class AnnonceController {
    private final AnnonceService annonceService;

    @PostMapping(consumes = {APPLICATION_JSON_VALUE, MULTIPART_FORM_DATA_VALUE, APPLICATION_OCTET_STREAM_VALUE})
    public void creeAnnonce(@RequestPart Annonce annonce, @RequestParam("file") MultipartFile[] file) {
        this.annonceService.creeAnnonce(annonce, file);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<Annonce> findAll() {
        return this.annonceService.findAll();
    }

    @GetMapping(path = "historique", produces = APPLICATION_JSON_VALUE)
    public List<Annonce> findHistorique() {
        return this.annonceService.historique();
    }

    @PostMapping(path = "favoris/{idAnnonce}")
    public void ajoutFavoris(@PathVariable int idAnnonce) {
        this.annonceService.ajoutFavoris(idAnnonce);
    }

    @GetMapping(path = "favoris", produces = APPLICATION_JSON_VALUE)
    public List<Annonce> findAllFavoris() {
        return this.annonceService.findAllFavoris();
    }

    @GetMapping(path = "image")
    public List<Img> findAllImageUrl() {
        return this.annonceService.findAllImageUrl();
    }

    @GetMapping(path = "recherche/date")
    public List<Annonce> researchByDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return this.annonceService.researchByDate(date.toString());
    }

    @GetMapping(path = "recherche/prix")
    public List<Annonce> researchByPrix(@RequestParam("prix") double prix) {
        return this.annonceService.researchByPrix(prix);
    }

    @GetMapping(path = "recherche/marque")
    public List<Annonce> researchByMarque(@RequestParam("marque") String marque) {
        return this.annonceService.researchByMarque(marque);
    }

    @GetMapping(path = "recherche/modele")
    public List<Annonce> researchByModele(@RequestParam("modele") String modele) {
        return this.annonceService.researchByModele(modele);
    }
}
