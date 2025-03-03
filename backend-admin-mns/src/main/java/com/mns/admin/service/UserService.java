package com.mns.admin.service;

import com.mns.admin.dto.AuthRequest;
import com.mns.admin.model.Utilisateur;
import com.mns.admin.model.UtilisateurSession;
import com.mns.admin.model.VerificationToken;
import com.mns.admin.repository.UserRepository;
import com.mns.admin.repository.UserSessionRepository;
import com.mns.admin.repository.VerrificationTokenRepository;
import com.mns.admin.security.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final UserSessionRepository userSessionRepository;
    private final VerrificationTokenRepository verificationTokenRepository;
    private final EmailService emailService;
    private PasswordEncoder passwordEncoder;

    @Value("${jwt.expiration_time}")
    private long expirationTime;

    public UserService(UserRepository userRepository, JwtUtil jwtUtil, UserSessionRepository userSessionRepository, VerrificationTokenRepository verificationTokenRepository, EmailService emailService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.userSessionRepository = userSessionRepository;
        this.verificationTokenRepository = verificationTokenRepository;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }

    public String authenticateUser(AuthRequest authRequest) {
        Utilisateur utilisateur = userRepository.findByEmailUtilisateur(authRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        if (!utilisateur.isEmailVerified()) {
            sendEmailVerification(utilisateur);
            throw new RuntimeException("Veuillez vérifier votre email.");
        }

        // Vérification du mot de passe
        if (!passwordEncoder.matches(authRequest.getMotDePasse(), utilisateur.getMotDePasseUtilisateur())) {
            throw new RuntimeException("Mot de passe incorrect");
        }

        String role = utilisateur.getRole().getNomRole();
        String token = generateUserSession(utilisateur, role);

        System.out.println("Rôle de l'utilisateur : " + role);

        return token;
    }

    public void sendEmailVerification(Utilisateur utilisateur) {
        if (utilisateur.isEmailVerified()) {
            throw new RuntimeException("L'email est déjà vérifié.");
        }
        // Générer le token de vérification
        String token = jwtUtil.generateToken(utilisateur.getEmailUtilisateur(), utilisateur.getRole().getNomRole());

        // Enregistrer le token dans la base de données avec une durée d'expiration (par exemple, 15 minutes)
        LocalDateTime expirationDate = LocalDateTime.now().plusMinutes(15);
        VerificationToken verificationToken = new VerificationToken(token, utilisateur, expirationDate);
        verificationTokenRepository.save(verificationToken);

        // Créer le lien de vérification
        String verificationLink = "http://admin-mns:8080/api/auth/verify-email?token=" + token;
        String emailBody = "<p>Cliquez sur le lien ci-dessous pour valider votre email :</p>" +
                "<a href='" + verificationLink + "'>Vérifier mon compte</a>";

        // Envoyer l'email
        emailService.sendEmail(utilisateur.getEmailUtilisateur(), "Vérification de votre compte", emailBody);
    }

    public String verifyEmail(String token) {
        String email = jwtUtil.getEmailFromToken(token);
        Utilisateur utilisateur = userRepository.findByEmailUtilisateur(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        // Vérifier si le token est expiré
        if (jwtUtil.isTokenExpired(token)) {
            throw new RuntimeException("Le token de vérification a expiré.");
        }

        // Ajouter l'utilisateur à la table des sessions actives
        if (!userSessionRepository.existsByUtilisateur(utilisateur)) {
            generateUserSession(utilisateur, utilisateur.getRole().getNomRole());
        }

        // Mettre à jour l'email comme vérifié
        utilisateur.setEmailVerified(true);
        userRepository.save(utilisateur);

        return jwtUtil.generateToken(utilisateur.getEmailUtilisateur(), utilisateur.getRole().getNomRole());
    }

    public String generateUserSession(Utilisateur utilisateur, String role) {
        String token = jwtUtil.generateToken(utilisateur.getEmailUtilisateur(), role);
        UtilisateurSession session = new UtilisateurSession(utilisateur, token, LocalDateTime.now().plusHours(1));
        userSessionRepository.save(session);
        return token;
    }
}
