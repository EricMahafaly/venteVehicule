package com.Eric.venteVehicule.security;


import com.Eric.venteVehicule.model.Jwt;
import com.Eric.venteVehicule.service.UtilisateurService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Service
public class JwtFilter extends OncePerRequestFilter {
    private UtilisateurService utilisateurService;
    private JwtService jwtService;

    public JwtFilter(UtilisateurService utilisateurService, JwtService jwtService) {
        this.utilisateurService = utilisateurService;
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = null;
        Jwt tokenDansLaBDD = null;
        String userName = null;
        boolean isTokenExpired = true;

        final String authorization = request.getHeader("Authorization");
        if(authorization != null && authorization.startsWith("Bearer ")) {
            token = authorization.substring(7);
            tokenDansLaBDD = this.jwtService.tokenByValue(token);
            isTokenExpired = jwtService.isTokenExpired(token);
            userName = jwtService.extractUserName(token);
        }
        if(!isTokenExpired && tokenDansLaBDD.getUtilisateur().getNomUtilisateur().equals(userName) && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = utilisateurService.loadUserByUsername(userName);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }
}
