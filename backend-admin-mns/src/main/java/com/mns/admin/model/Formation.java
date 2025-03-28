package com.mns.admin.model;

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
@Table(name = "formation")
public class Formation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_formation")
    private Long idFormation;

    @Column(name = "nom_formation", nullable = false, length = 100)
    private String nomFormation;

    @Column(name = "description_formation")
    private String descriptionFormation;

    @Column(name = "duree_formation", nullable = false)
    private Integer dureeFormation;

    @Column(name = "date_debut_formation", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateDebutFormation;

    @Column(name = "date_fin_formation", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateFinFormation;

    public Long getIdFormation() {
        return idFormation;
    }

    public void setIdFormation(Long idFormation) {
        this.idFormation = idFormation;
    }

    public String getNomFormation() {
        return nomFormation;
    }

    public void setNomFormation(String nomFormation) {
        this.nomFormation = nomFormation;
    }

    public String getDescriptionFormation() {
        return descriptionFormation;
    }

    public void setDescriptionFormation(String descriptionFormation) {
        this.descriptionFormation = descriptionFormation;
    }

    public Integer getDureeFormation() {
        return dureeFormation;
    }

    public void setDureeFormation(Integer dureeFormation) {
        this.dureeFormation = dureeFormation;
    }

    public LocalDateTime getDateDebutFormation() {
        return dateDebutFormation;
    }

    public void setDateDebutFormation(LocalDateTime dateDebutFormation) {
        this.dateDebutFormation = dateDebutFormation;
    }

    public LocalDateTime getDateFinFormation() {
        return dateFinFormation;
    }

    public void setDateFinFormation(LocalDateTime dateFinFormation) {
        this.dateFinFormation = dateFinFormation;
    }
}