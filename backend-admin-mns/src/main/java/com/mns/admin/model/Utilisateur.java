package com.mns.admin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "utilisateur")
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_utilisateur")
    private Long idUtilisateur;

    @Column(name = "nom_utilisateur", nullable = false, length = 50)
    private String nomUtilisateur;

    @Column(name = "prenom_utilisateur", nullable = false, length = 50)
    private String prenomUtilisateur;

    @Column(name = "email_utilisateur", nullable = false, length = 100, unique = true)
    private String emailUtilisateur;

    @Column(name = "motDePasse_utilisateur", nullable = false)
    private String motDePasseUtilisateur;

    @Column(name = "dateCreation_utilisateur", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateCreationUtilisateur;

    @Column(name = "dateMiseAJour_utilisateur")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateMiseAJourUtilisateur;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    @JsonIgnore
    private Role role;

    @PrePersist
    protected void onCreate() {
        dateCreationUtilisateur = LocalDateTime.now();
        dateMiseAJourUtilisateur = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        dateMiseAJourUtilisateur = LocalDateTime.now();
    }
}