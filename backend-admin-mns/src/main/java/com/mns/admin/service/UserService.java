package com.mns.admin.service;

import com.mns.admin.dto.AuthRequest;
import com.mns.admin.dto.PasswordChangeDto;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtUtil jwtUtil;
    private final UserSessionRepository userSessionRepository;
    private final VerrificationTokenRepository verificationTokenRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

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
            throw new RuntimeException("Veuillez vérifier votre email. Un lien de vérification vous a été renvoyé.");
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
        sendEmailVerification(utilisateur, null);
    }

    public void sendEmailVerification(Utilisateur utilisateur, String motDePasseTemporaire) {
        if (utilisateur.isEmailVerifie()) {
            throw new RuntimeException("L'email est déjà vérifié.");
        }

        String token = jwtUtil.generateToken(utilisateur.getEmailUtilisateur(), utilisateur.getRole().getNomRole());
        LocalDateTime expirationDate = LocalDateTime.now().plusMinutes(15);

        VerificationToken verificationToken = new VerificationToken(token, utilisateur, expirationDate);
        verificationTokenRepository.save(verificationToken);

        String verificationLink = "http://admin-mns:8080/api/auth/verify-email?token=" + token;

        StringBuilder emailBody = new StringBuilder();
        emailBody.append("<p>Bonjour ").append(utilisateur.getPrenomUtilisateur()).append(",</p>")
                .append("<p>Merci de valider votre compte en cliquant sur le lien suivant :</p>")
                .append("<p><a href='").append(verificationLink).append("'>Vérifier mon compte</a></p>");

        if (motDePasseTemporaire != null) {
            emailBody.append("<p>Voici votre mot de passe temporaire : <strong>")
                    .append(motDePasseTemporaire).append("</strong></p>")
                    .append("<p><strong>Veuillez le modifier une fois connecté(e) dans votre espace personnel.</strong></p>");
        }

        emailBody.append("<p>Ce lien expirera dans 15 minutes.</p>");

        emailService.sendEmail(utilisateur.getEmailUtilisateur(), "Vérification de votre compte", emailBody.toString());
    }

    public ResponseEntity<?> verifyEmail(String token) {
        String email = jwtUtil.getEmailFromToken(token);
        Utilisateur utilisateur = userRepository.findByEmailUtilisateur(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        if (jwtUtil.isTokenExpired(token)) {
            return ResponseEntity.status(HttpStatus.FOUND)
                    .location(URI.create("http://admin-mns/frontend-admin-mns/views/login.php?status=expired"))
                    .build();
        }

        utilisateur.setEmailVerifie(true);
        userRepository.save(utilisateur);

        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create("http://admin-mns/frontend-admin-mns/views/login.php?status=verified"))
                .build();
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

        sendEmailVerification(utilisateur, tempPassword);

        return utilisateur;
    }

    public void changePassword(String useremail, PasswordChangeDto passwordChangeDto) {
        Utilisateur user = userRepository.findByEmailUtilisateur(useremail).orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));

        if (!isPasswordStrong(passwordChangeDto.getNewPassword())) {
            throw new RuntimeException("Le mot de passe n'est pas assez fort");
        }

        if (!passwordChangeDto.getNewPassword().equals(passwordChangeDto.getConfirmNewPassword())) {
            throw new RuntimeException("Les nouveaux mot de passe ne correspondent pas");
        }

        String hashedPassword = passwordEncoder.encode(passwordChangeDto.getNewPassword());
        System.out.println("Nouveau mot de passe hashé : " + hashedPassword);

        user.setMotDePasseUtilisateur(hashedPassword);
        userRepository.save(user);
        System.out.println("Mot de passe enregistré en base.");
    }

    // Au moins 8 caractères, 1 chiffre, 1 minuscule, 1 majuscule et 1 caractère spécial
    private boolean isPasswordStrong(String password) {
        String pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%&!?^+=_;:§]).{8,}$";
        return Pattern.matches(pattern, password);
    }

    public void updateRoleToStagiaire(Long userId) {
        Utilisateur utilisateur = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        Role stagiaireRole = roleRepository.findByNomRole("Stagiaire").orElseThrow(() -> new RuntimeException("Role non trouvé"));

        utilisateur.setRole(stagiaireRole);
        userRepository.save(utilisateur);
    }
}
