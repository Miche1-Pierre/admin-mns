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
@Table(name = "inscription", uniqueConstraints = @UniqueConstraint(columnNames = {"id_stagiaire", "id_formation"}))
public class Inscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inscription")
    private Long idInscription;

    @ManyToOne
    @JoinColumn(name = "id_stagiaire", nullable = false)
    @JsonIgnore
    private Utilisateur stagiaire;

    @ManyToOne
    @JoinColumn(name = "id_formation", nullable = false)
    @JsonIgnore
    private Formation formation;

    @Column(name = "date_inscription", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateInscription;

    @ManyToOne
    @JoinColumn(name = "id_statut", nullable = false)
    private Statut statut;

    public enum InscriptionEtat {
        EN_ATTENTE,
        VALIDE,
        REFUSE,
        SUPPRIME
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "etat_inscription", nullable = false)
    private InscriptionEtat etatInscription = InscriptionEtat.EN_ATTENTE;

    public Long getIdInscription() {
        return idInscription;
    }

    public void setIdInscription(Long idInscription) {
        this.idInscription = idInscription;
    }

    public Utilisateur getStagiaire() {
        return stagiaire;
    }

    public void setStagiaire(Utilisateur stagiaire) {
        this.stagiaire = stagiaire;
    }

    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    public LocalDateTime getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(LocalDateTime dateInscription) {
        this.dateInscription = dateInscription;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public InscriptionEtat getEtatInscription() {
        return etatInscription;
    }

    public void setEtatInscription(InscriptionEtat etatInscription) {
        this.etatInscription = etatInscription;
    }
}