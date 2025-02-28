package com.mns.admin.service;

import com.mns.admin.model.Utilisateur;
import com.mns.admin.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Utilisateur login(Utilisateur utilisateur) {
        Utilisateur utilisateurDb = userRepository.findByEmailUtilisateur(utilisateur.getEmailUtilisateur())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        if (passwordEncoder.matches(utilisateur.getMotDePasseUtilisateur(), utilisateurDb.getMotDePasseUtilisateur())) {
            return utilisateurDb;
        } else {
            throw new RuntimeException("Mot de passe incorrect");
        }
    }
}
