package com.mns.admin.controller;

import com.mns.admin.dto.AuthRequest;
import com.mns.admin.dto.CandidatureDto;
import com.mns.admin.dto.PasswordChangeDto;
import com.mns.admin.model.Utilisateur;
import com.mns.admin.security.JwtUtil;
import com.mns.admin.service.CandidatureService;
import com.mns.admin.service.SessionService;
import com.mns.admin.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://admin-mns")
public class AuthController {
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final CandidatureService candidatureService;
    private final SessionService sessionsService;

    public AuthController(UserService userService, JwtUtil jwtUtil, CandidatureService candidatureService, SessionService sessionService) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.candidatureService = candidatureService;
        this.sessionsService = sessionService;
    }

    @GetMapping("/verify-email")
    public ResponseEntity<Void> verifyEmail(@RequestParam String token) {
        try {
            userService.verifyEmail(token);
            return ResponseEntity.status(HttpStatus.FOUND)
                    .location(URI.create("http://admin-mns/frontend-admin-mns/views/login.php?status=verified"))
                    .build();
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.FOUND)
                    .location(URI.create("http://admin-mns/frontend-admin-mns/views/login.php?status=expired"))
                    .build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        try {
            String token = userService.authenticateUser(authRequest);
            return ResponseEntity.ok(token);
        } catch (RuntimeException e) {
            if (e.getMessage().equals("Veuillez vérifier votre email.")) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body("Un email de vérification a été envoyé. Veuillez vérifier votre email.");
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            sessionsService.invalidateSession(token);
            return ResponseEntity.ok("Déconnexion réussie");
        }
        return ResponseEntity.badRequest().body("Token manquant");
    }

    @PostMapping(value = "/candidature", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createCandidature(@ModelAttribute CandidatureDto candidatureDto) {
        try {
            candidatureService.createCandidature(candidatureDto);
            return ResponseEntity.ok("Candidature créée avec succès.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@AuthenticationPrincipal(expression = "username") String email,
                                            @RequestBody PasswordChangeDto passwordChangeDto) {
        System.out.println("Email reçu depuis @AuthenticationPrincipal : " + email);
        if (email == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Utilisateur non authentifié");
        }

        System.out.println("Utilisateur authentifié : " + email);
        try {
            userService.changePassword(email, passwordChangeDto);
            return ResponseEntity.ok("Mot de passe mis à jour avec succès");
        } catch (Exception e) {
            System.err.println("Erreur lors de la mise à jour du mot de passe : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
