package com.mns.admin.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "verification_token")
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String token;

    @OneToOne
    @JoinColumn(name = "utilisateur_id", nullable = false)
    private Utilisateur utilisateur;

    @Column(nullable = false)
    private LocalDateTime expirationDate;

    public VerificationToken() {}

    public VerificationToken(String token, Utilisateur utilisateur, LocalDateTime expirationDate) {
        this.token = token;
        this.utilisateur = utilisateur;
        this.expirationDate = expirationDate;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expirationDate);
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }
}
