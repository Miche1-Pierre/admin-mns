package com.mns.admin_mns.models;

import jakarta.persistence.*;
import lombok.*;

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
    private Date dateCreationUtilisateur;

    @Column(name = "dateMiseAJour_utilisateur")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateMiseAJourUtilisateur;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @PrePersist
    protected void onCreate() {
        dateCreationUtilisateur = new Date();
        dateMiseAJourUtilisateur = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        dateMiseAJourUtilisateur = new Date();
    }
}