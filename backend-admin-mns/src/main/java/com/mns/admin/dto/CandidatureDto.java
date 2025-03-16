package com.mns.admin.dto;

import org.springframework.web.multipart.MultipartFile;

public class CandidatureDto {
    private String nom;
    private String prenom;
    private String email;
    private MultipartFile cv;
    private MultipartFile lettre;
    private String message;
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
}