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
}