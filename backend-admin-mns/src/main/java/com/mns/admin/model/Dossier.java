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

    @Column(name = "date_creation_dossier", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateCreationDossier;

    @Column(name = "date_validation_dossier")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateValidationDossier;

    @ManyToOne
    @JoinColumn(name = "id_statut", referencedColumnName = "idStatut", nullable = false)
    private Statut statut;
}