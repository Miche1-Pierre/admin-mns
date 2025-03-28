package com.mns.admin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "absence")
public class Absence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_absence")
    private Long idAbsence;

    @ManyToOne
    @JoinColumn(name = "id_stagiaire", nullable = false)
    @JsonIgnore
    private Utilisateur stagiaire;

    @ManyToOne
    @JoinColumn(name = "id_typeAbsence", nullable = false)
    @JsonIgnore
    private TypeAbsence typeAbsence;

    @Column(name = "date_debut_absence", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateDebutAbsence;

    @Column(name = "date_fin_absence")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateFinAbsence;

    @Column(name = "statut_absence", nullable = false)
    private String statutAbsence;

    @Column(name = "justifie_absence", nullable = false)
    private boolean justifieAbsence;

    @Column(name = "justificatif_path")
    private String justificatifPath;

    @OneToMany(mappedBy = "absence", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Justificatif> justificatifs = new ArrayList<>();

    public enum AbsenceStatus {
        EN_ATTENTE,
        VALIDE,
        REFUSE,
        SUPPRIME
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "etat_absence", nullable = false)
    private AbsenceStatus etatAbsence = AbsenceStatus.EN_ATTENTE;

    public Long getIdAbsence() {
        return idAbsence;
    }

    public void setIdAbsence(Long idAbsence) {
        this.idAbsence = idAbsence;
    }

    public Utilisateur getStagiaire() {
        return stagiaire;
    }

    public void setStagiaire(Utilisateur stagiaire) {
        this.stagiaire = stagiaire;
    }

    public TypeAbsence getTypeAbsence() {
        return typeAbsence;
    }

    public void setTypeAbsence(TypeAbsence typeAbsence) {
        this.typeAbsence = typeAbsence;
    }

    public LocalDateTime getDateDebutAbsence() {
        return dateDebutAbsence;
    }

    public void setDateDebutAbsence(LocalDateTime dateDebutAbsence) {
        this.dateDebutAbsence = dateDebutAbsence;
    }

    public LocalDateTime getDateFinAbsence() {
        return dateFinAbsence;
    }

    public void setDateFinAbsence(LocalDateTime dateFinAbsence) {
        this.dateFinAbsence = dateFinAbsence;
    }

    public String getStatutAbsence() {
        return statutAbsence;
    }

    public void setStatutAbsence(String statutAbsence) {
        this.statutAbsence = statutAbsence;
    }

    public boolean isJustifieAbsence() {
        return justifieAbsence;
    }

    public void setJustifieAbsence(boolean justifieAbsence) {
        this.justifieAbsence = justifieAbsence;
    }

    public String getJustificatifPath() {
        return justificatifPath;
    }

    public void setJustificatifPath(String justificatifPath) {
        this.justificatifPath = justificatifPath;
    }

    public AbsenceStatus getEtatAbsence() {
        return etatAbsence;
    }

    public void setEtatAbsence(AbsenceStatus etatAbsence) {
        this.etatAbsence = etatAbsence;
    }

    public List<Justificatif> getJustificatifs() {
        return justificatifs;
    }

    public void setJustificatifs(List<Justificatif> justificatifs) {
        this.justificatifs = justificatifs;
    }
}