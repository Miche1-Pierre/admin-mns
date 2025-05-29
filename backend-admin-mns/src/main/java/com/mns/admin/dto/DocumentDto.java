package com.mns.admin.dto;

import com.mns.admin.model.TypeDocument;

import java.time.LocalDateTime;

public class DocumentDto {
    private Long id;
    private String nom;
    private String type;
    private LocalDateTime dateDepot;
    private LocalDateTime dateLimite;

    public DocumentDto(Long id, String nom, TypeDocument type, LocalDateTime dateDepot, LocalDateTime dateLimite) {
        this.id = id;
        this.nom = nom;
        this.type = type.getNomTypeDocument();
        this.dateDepot = dateDepot;
        this.dateLimite = dateLimite;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public LocalDateTime getDateDepot() { return dateDepot; }
    public void setDateDepot(LocalDateTime dateDepot) { this.dateDepot = dateDepot; }

    public LocalDateTime getDateLimite() { return dateLimite; }
    public void setDateLimite(LocalDateTime dateLimite) { this.dateLimite = dateLimite; }
}
