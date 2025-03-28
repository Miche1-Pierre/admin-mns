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
@Table(name = "justificatif")
public class Justificatif {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_justificatif")
    private Long idJustificatif;

    @ManyToOne
    @JoinColumn(name = "id_absence", nullable = false)
    @JsonIgnore
    private Absence absence;

    @ManyToOne
    @JoinColumn(name = "id_utilisateur", nullable = false)
    private Utilisateur utilisateur;

    @Column(name = "type_document_justificatif", nullable = false, length = 50)
    private String typeDocumentJustificatif;

    @Column(name = "date_depot_justificatif", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateDepotJustificatif;

    @ManyToOne
    @JoinColumn(name = "id_statut", referencedColumnName = "idStatut", nullable = false)
    private Statut statut;

    public Long getIdJustificatif() {
        return idJustificatif;
    }

    public void setIdJustificatif(Long idJustificatif) {
        this.idJustificatif = idJustificatif;
    }

    public Absence getAbsence() {
        return absence;
    }

    public void setAbsence(Absence absence) {
        this.absence = absence;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getTypeDocumentJustificatif() {
        return typeDocumentJustificatif;
    }

    public void setTypeDocumentJustificatif(String typeDocumentJustificatif) {
        this.typeDocumentJustificatif = typeDocumentJustificatif;
    }

    public LocalDateTime getDateDepotJustificatif() {
        return dateDepotJustificatif;
    }

    public void setDateDepotJustificatif(LocalDateTime dateDepotJustificatif) {
        this.dateDepotJustificatif = dateDepotJustificatif;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }
}