package com.mns.admin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mns.admin.model.Inscription;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CandidatureDto {
    private Long id;
    private String nom;
    private String prenom;
    private String email;

    @JsonProperty("date_inscription")
    private LocalDateTime dateInscription;

    private MultipartFile cv;
    private MultipartFile lettre;
    private String message;
    private Inscription.InscriptionEtat inscriptionEtat;
    private String formationNom;
    private Long formationId;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public MultipartFile getCv() {
        return cv;
    }

    public void setCv(MultipartFile cv) {
        this.cv = cv;
    }

    public MultipartFile getLettre() {
        return lettre;
    }

    public void setLettre(MultipartFile lettre) {
        this.lettre = lettre;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getFormationId() {
        return formationId;
    }

    public void setFormationId(Long formationId) {
        this.formationId = formationId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(LocalDateTime dateInscription) {
        this.dateInscription = dateInscription;
    }

    public String getFormationNom() {
        return formationNom;
    }

    public void setFormationNom(String formationNom) {
        this.formationNom = formationNom;
    }

    public Inscription.InscriptionEtat getInscriptionEtat() {
        return inscriptionEtat;
    }

    public void setInscriptionEtat(Inscription.InscriptionEtat inscriptionEtat) {
        this.inscriptionEtat = inscriptionEtat;
    }
}