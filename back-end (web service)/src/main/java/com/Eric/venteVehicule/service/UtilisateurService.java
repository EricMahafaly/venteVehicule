package com.Eric.venteVehicule.service;


import com.Eric.venteVehicule.model.Utilisateur;
import com.Eric.venteVehicule.repository.UtilisateurRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UtilisateurService implements UserDetailsService {
    private UtilisateurRepository utilisateurRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public void inscription(Utilisateur utilisateur) {
        Optional<Utilisateur> utilisateurOptional = this.utilisateurRepository.findByNomUtilisateur(utilisateur.getNomUtilisateur());
        if(utilisateurOptional.isPresent()) {
            throw new RuntimeException("Votre mail est deja utilise");
        }
        String mdpCrypter = this.passwordEncoder.encode(utilisateur.getMdp());
        utilisateur.setMdp(mdpCrypter);
        utilisateur.setActif(true);
        this.utilisateurRepository.save(utilisateur);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.utilisateurRepository
                .findByNomUtilisateur(username)
                .orElseThrow(() -> new UsernameNotFoundException("nom utilisateur non valide"));
    }
}
