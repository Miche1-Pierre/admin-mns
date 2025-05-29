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
@Table(name = "document")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_document")
    private Long idDocument;

    @ManyToOne
    @JoinColumn(name = "id_dossier", nullable = false)
    private Dossier dossier;

    @ManyToOne
    @JoinColumn(name = "id_typeDocument", nullable = false)
    private TypeDocument typeDocument;

    @JoinColumn(name = "nom_document", nullable = false)
    private String nomDocument;

    @JoinColumn(name = "nom_physique", nullable = false)
    private String nomPhysique;

    @ManyToOne
    @JoinColumn(name = "id_statut", nullable = false)
    private Statut statut;

    @ManyToOne
    @JoinColumn(name = "id_secret")
    @JsonIgnore
    private Secret secret;

    @Column(name = "date_depot_document", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateDepotDocument;

    @Column(name = "date_limite_document")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateLimiteDocument;

    @Column(name = "cle_chiffrement_document", nullable = false)
    private String cleChiffrement_document;

    @Column(name = "contenu_chiffre_document", nullable = false, columnDefinition = "LONGBLOB")
    @Lob
    private byte[] contenuChiffreDocument;

    public Long getIdDocument() {
        return idDocument;
    }

    public void setIdDocument(Long idDocument) {
        this.idDocument = idDocument;
    }

    public Dossier getDossier() {
        return dossier;
    }

    public void setDossier(Dossier dossier) {
        this.dossier = dossier;
    }

    public TypeDocument getTypeDocument() {
        return typeDocument;
    }

    public void setTypeDocument(TypeDocument typeDocument) {
        this.typeDocument = typeDocument;
    }

    public String getNomDocument() {
        return nomDocument;
    }

    public void setNomDocument(String nomDocument) {
        this.nomDocument = nomDocument;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public Secret getSecret() {
        return secret;
    }

    public void setSecret(Secret secret) {
        this.secret = secret;
    }

    public LocalDateTime getDateDepotDocument() {
        return dateDepotDocument;
    }

    public void setDateDepotDocument(LocalDateTime dateDepotDocument) {
        this.dateDepotDocument = dateDepotDocument;
    }

    public LocalDateTime getDateLimiteDocument() {
        return dateLimiteDocument;
    }

    public void setDateLimiteDocument(LocalDateTime dateLimiteDocument) {
        this.dateLimiteDocument = dateLimiteDocument;
    }

    public String getNomPhysique() {
        return nomPhysique;
    }

    public void setNomPhysique(String nomPhysique) {
        this.nomPhysique = nomPhysique;
    }

    public String getCleChiffrement_document() {
        return cleChiffrement_document;
    }

    public void setCleChiffrement_document(String cleChiffrement_document) {
        this.cleChiffrement_document = cleChiffrement_document;
    }

    public byte[] getContenuChiffreDocument() {
        return contenuChiffreDocument;
    }

    public void setContenuChiffreDocument(byte[] contenuChiffreDocument) {
        this.contenuChiffreDocument = contenuChiffreDocument;
    }
}