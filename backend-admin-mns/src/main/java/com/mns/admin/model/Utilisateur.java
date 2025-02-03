package com.mns.admin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
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

    //Getter Setter

    public Long getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Long idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public String getPrenomUtilisateur() {
        return prenomUtilisateur;
    }

    public void setPrenomUtilisateur(String prenomUtilisateur) {
        this.prenomUtilisateur = prenomUtilisateur;
    }

    public String getEmailUtilisateur() {
        return emailUtilisateur;
    }

    public void setEmailUtilisateur(String emailUtilisateur) {
        this.emailUtilisateur = emailUtilisateur;
    }

    public String getMotDePasseUtilisateur() {
        return motDePasseUtilisateur;
    }

    public void setMotDePasseUtilisateur(String motDePasseUtilisateur) {
        this.motDePasseUtilisateur = motDePasseUtilisateur;
    }

    public LocalDateTime getDateCreationUtilisateur() {
        return dateCreationUtilisateur;
    }

    public void setDateCreationUtilisateur(LocalDateTime dateCreationUtilisateur) {
        this.dateCreationUtilisateur = dateCreationUtilisateur;
    }

    public LocalDateTime getDateMiseAJourUtilisateur() {
        return dateMiseAJourUtilisateur;
    }

    public void setDateMiseAJourUtilisateur(LocalDateTime dateMiseAJourUtilisateur) {
        this.dateMiseAJourUtilisateur = dateMiseAJourUtilisateur;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}