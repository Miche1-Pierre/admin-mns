package com.mns.admin.service;

import com.mns.admin.dto.AuthRequest;
import com.mns.admin.model.Role;
import com.mns.admin.model.Utilisateur;
import com.mns.admin.model.UtilisateurSession;
import com.mns.admin.model.VerificationToken;
import com.mns.admin.repository.RoleRepository;
import com.mns.admin.repository.UserRepository;
import com.mns.admin.repository.UserSessionRepository;
import com.mns.admin.repository.VerrificationTokenRepository;
import com.mns.admin.security.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtUtil jwtUtil;
    private final UserSessionRepository userSessionRepository;
    private final VerrificationTokenRepository verificationTokenRepository;
    private final EmailService emailService;
    private PasswordEncoder passwordEncoder;

    @Value("${jwt.expiration_time}")
    private long expirationTime;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, JwtUtil jwtUtil, UserSessionRepository userSessionRepository, VerrificationTokenRepository verificationTokenRepository, EmailService emailService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.jwtUtil = jwtUtil;
        this.userSessionRepository = userSessionRepository;
        this.verificationTokenRepository = verificationTokenRepository;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }

    public String authenticateUser(AuthRequest authRequest) {
        Utilisateur utilisateur = userRepository.findByEmailUtilisateur(authRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        if (!utilisateur.isEmailVerifie()) {
            sendEmailVerification(utilisateur);
            throw new RuntimeException("Veuillez vérifier votre email.");
        }

        if (!passwordEncoder.matches(authRequest.getMotDePasse(), utilisateur.getMotDePasseUtilisateur())) {
            throw new RuntimeException("Mot de passe incorrect");
        }

        String role = utilisateur.getRole().getNomRole();
        String token = generateUserSession(utilisateur, role);

        System.out.println("Rôle de l'utilisateur : " + role);

        return token;
    }

    public void sendEmailVerification(Utilisateur utilisateur) {
        if (utilisateur.isEmailVerifie()) {
            throw new RuntimeException("L'email est déjà vérifié.");
        }
        String token = jwtUtil.generateToken(utilisateur.getEmailUtilisateur(), utilisateur.getRole().getNomRole());

        LocalDateTime expirationDate = LocalDateTime.now().plusMinutes(15);
        VerificationToken verificationToken = new VerificationToken(token, utilisateur, expirationDate);
        verificationTokenRepository.save(verificationToken);

        String verificationLink = "http://admin-mns:8080/api/auth/verify-email?token=" + token;
        String emailBody = "<p>Cliquez sur le lien ci-dessous pour valider votre email :</p>" +
                "<a href='" + verificationLink + "'>Vérifier mon compte</a>";

        emailService.sendEmail(utilisateur.getEmailUtilisateur(), "Vérification de votre compte", emailBody);
    }

    public String verifyEmail(String token) {
        String email = jwtUtil.getEmailFromToken(token);
        Utilisateur utilisateur = userRepository.findByEmailUtilisateur(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        if (jwtUtil.isTokenExpired(token)) {
            throw new RuntimeException("Le token de vérification a expiré.");
        }

        if (!userSessionRepository.existsByUtilisateur(utilisateur)) {
            generateUserSession(utilisateur, utilisateur.getRole().getNomRole());
        }

        utilisateur.setEmailVerifie(true);
        userRepository.save(utilisateur);

        return jwtUtil.generateToken(utilisateur.getEmailUtilisateur(), utilisateur.getRole().getNomRole());
    }

    public String generateUserSession(Utilisateur utilisateur, String role) {
        String token = jwtUtil.generateToken(utilisateur.getEmailUtilisateur(), role);
        UtilisateurSession session = new UtilisateurSession(utilisateur, token, LocalDateTime.now().plusHours(1));
        userSessionRepository.save(session);
        return token;
    }

    public Utilisateur getUserFromToken(String token) {
        if (jwtUtil.isTokenExpired(token)) {
            throw new RuntimeException("Le token est expiré.");
        }

        String email = jwtUtil.extractUsername(token);
        return userRepository.findByEmailUtilisateur(email).orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
    }

    public Utilisateur createUserFromCandidature(String nom, String prenom, String email) {
        if (userRepository.findByEmailUtilisateur(email).isPresent()) {
            throw new RuntimeException("Un utilisateur avec cet email existe déjà.");
        }

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNomUtilisateur(nom);
        utilisateur.setPrenomUtilisateur(prenom);
        utilisateur.setEmailUtilisateur(email);
        String tempPassword = UUID.randomUUID().toString().substring(0, 8);
        utilisateur.setMotDePasseUtilisateur(passwordEncoder.encode(tempPassword));
        System.out.println("Mot de passe temporaire : " + tempPassword);

        Role candidateRole = roleRepository.findByNomRole("CANDIDAT")
                .orElseGet(() -> {
                    Role newRole = new Role();
                    newRole.setNomRole("CANDIDAT");
                    newRole.setDescriptionRole("Rôle pour les candidats");
                    return roleRepository.save(newRole);
                });

        utilisateur.setRole(candidateRole);
        utilisateur.setEmailVerifie(false);
        userRepository.save(utilisateur);

        sendEmailVerification(utilisateur);

        return utilisateur;
    }

}
