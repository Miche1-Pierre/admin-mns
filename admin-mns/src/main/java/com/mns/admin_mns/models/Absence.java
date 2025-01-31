package com.mns.admin_mns.models;

import jakarta.persistence.*;
import lombok.*;

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
    private Utilisateur stagiaire;

    @ManyToOne
    @JoinColumn(name = "id_typeAbsence", nullable = false)
    private TypeAbsence typeAbsence;

    @Column(name = "dateDebut_absence", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDebutAbsence;

    @Column(name = "dateFin_absence")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateFinAbsence;

    @Column(name = "justifie_absence", nullable = false)
    private boolean justifieAbsence;
}
