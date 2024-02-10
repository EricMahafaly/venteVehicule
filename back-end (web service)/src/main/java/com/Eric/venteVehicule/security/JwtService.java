package com.Eric.venteVehicule.security;


import com.Eric.venteVehicule.model.Jwt;
import com.Eric.venteVehicule.model.Utilisateur;
import com.Eric.venteVehicule.repository.JwtRepository;
import com.Eric.venteVehicule.service.UtilisateurService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@AllArgsConstructor
@Service
public class JwtService {
    public static final String BEARER = "bearer";
    private final String ENCRYPTION_KEY = "18a27d7dc1011f47620a5440930ce7b89ba2db7d8ddecd14304cde142792163e";
    private UtilisateurService utilisateurService;
    private JwtRepository jwtRepository;

    public Jwt tokenByValue(String valeur) {
        return this.jwtRepository.findByValeur(valeur)
                .orElseThrow(() -> new RuntimeException("Token inconnu"));
    }

    public Map<String, String> generate(String userName) {
        Utilisateur utilisateur = (Utilisateur) this.utilisateurService.loadUserByUsername(userName);
        final Map<String, String> jwtMap = this.generateJwt(utilisateur);
        final Jwt jwt = Jwt
                .builder()
                .valeur(jwtMap.get(BEARER))
                .desactive(false)
                .expire(false)
                .utilisateur(utilisateur)
                .build();
        this.jwtRepository.save(jwt);

        return jwtMap;
    }

    private Map<String, String> generateJwt(Utilisateur utilisateur) {
        final long currentTime = System.currentTimeMillis();
        final long expirationTime = currentTime + 30 * 60 * 1000;
        Map<String, Object> claims = Map.of(
                Claims.EXPIRATION, new Date(expirationTime),
                Claims.SUBJECT, utilisateur.getNomUtilisateur()
        );
        final String bearer = Jwts.builder()
                .setIssuedAt(new Date(currentTime))
                .setExpiration(new Date(expirationTime))
                .setSubject(utilisateur.getNomUtilisateur())
                .setClaims(claims)
                .signWith(this.getKey(), SignatureAlgorithm.HS256)
                .compact();
        return Map.of(BEARER, bearer);
    }

    private Key getKey() {
        final byte[] decoder = Decoders.BASE64.decode(this.ENCRYPTION_KEY);
        return Keys.hmacShaKeyFor(decoder);
    }

    public String extractUserName(String token) {
        return this.getClaims(token, Claims::getSubject);
    }

    public boolean isTokenExpired(String token) {
        Date expirationDate = this.getClaims(token, Claims::getExpiration);
        return expirationDate.before(new Date());
    }

    private Date getExpirationDateFromToken(String token) {
        return this.getClaims(token, Claims::getExpiration);
    }

    private <T> T getClaims(String token, Function<Claims, T> function) {
        Claims claims = getAllClaims(token);
        return function.apply(claims);
    }

    private Claims getAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(this.getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public void deconnexion() {
    }
}
