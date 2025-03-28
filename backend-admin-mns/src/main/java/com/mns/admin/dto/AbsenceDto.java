package com.mns.admin.dto;

import com.mns.admin.model.Absence;

import java.time.LocalDateTime;

public class AbsenceDto {
    private Long idAbsence;
    private LocalDateTime dateDebutAbsence;
    private LocalDateTime dateFinAbsence;
    private String statutAbsence;
    private boolean justifieAbsence;
    private String utilisateur;
    private String typeAbsence;
    private Absence.AbsenceStatus etatAbsence;

    // Constructeur
    public AbsenceDto(Long idAbsence, LocalDateTime dateDebutAbsence, LocalDateTime dateFinAbsence,
                      String statutAbsence, boolean justifieAbsence, String utilisateur, String typeAbsence, Absence.AbsenceStatus etatAbsence) {
        this.idAbsence = idAbsence;
        this.dateDebutAbsence = dateDebutAbsence;
        this.dateFinAbsence = dateFinAbsence;
        this.statutAbsence = statutAbsence;
        this.justifieAbsence = justifieAbsence;
        this.utilisateur = utilisateur;
        this.typeAbsence = typeAbsence;
        this.etatAbsence = etatAbsence;
    }

    // Getters et Setters
    public Absence.AbsenceStatus getEtatAbsence() {
        return etatAbsence;
    }

    public void setEtatAbsence(Absence.AbsenceStatus etatAbsence) {
        this.etatAbsence = etatAbsence;
    }

    public Long getIdAbsence() {
        return idAbsence;
    }

    public void setIdAbsence(Long idAbsence) {
        this.idAbsence = idAbsence;
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

    public String getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(String utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getTypeAbsence() {
        return typeAbsence;
    }

    public void setTypeAbsence(String typeAbsence) {
        this.typeAbsence = typeAbsence;
    }
}
