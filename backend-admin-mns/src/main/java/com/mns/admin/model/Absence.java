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

    @Column(name = "dateDebut_absence", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateDebutAbsence;

    @Column(name = "dateFin_absence")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateFinAbsence;

    @Column(name = "justifie_absence", nullable = false)
    private boolean justifieAbsence;
}