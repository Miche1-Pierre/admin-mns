package com.mns.admin.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "utilisateur_session")
public class UtilisateurSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id", nullable = false)
    private Utilisateur utilisateur;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private LocalDateTime creationDate;

    @Column(nullable = false)
    private LocalDateTime expirationDate;

    public UtilisateurSession() {}

    public UtilisateurSession(Utilisateur utilisateur, String token, LocalDateTime expirationDate) {
        this.utilisateur = utilisateur;
        this.token = token;
        this.creationDate = LocalDateTime.now();
        this.expirationDate = expirationDate;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expirationDate);
    }
}
