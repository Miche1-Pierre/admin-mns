package com.mns.admin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dossier")
public class Dossier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dossier")
    private Long idDossier;

    @ManyToOne
    @JoinColumn(name = "id_stagiaire", nullable = false)
    @JsonIgnore
    private Utilisateur stagiaire;

    @Column(name = "dateCreation_dossier", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreationDossier;

    @Column(name = "dateValidation_dossier")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateValidationDossier;

    @Column(name = "statut", nullable = false)
    @Enumerated(EnumType.STRING)
    private Statut statut;
}