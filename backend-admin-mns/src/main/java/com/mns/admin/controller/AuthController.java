package com.mns.admin.controller;

import com.mns.admin.dto.AuthRequest;
import com.mns.admin.model.Utilisateur;
import com.mns.admin.security.JwtUtil;
import com.mns.admin.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://admin-mns")
public class AuthController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/validate")
    public boolean validateToken(@RequestBody AuthRequest authRequest) {
        return jwtUtil.validateToken(authRequest.getToken());
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest authRequest) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmailUtilisateur(authRequest.getEmail());
        utilisateur.setMotDePasseUtilisateur(authRequest.getMotDePasse());
        Utilisateur authenticatedUser = userService.login(utilisateur);

        String token = jwtUtil.generateToken(authenticatedUser.getEmailUtilisateur());

        return token;
    }
}
