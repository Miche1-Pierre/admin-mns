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

    @Column(name = "typeDocument_justificatif", nullable = false, length = 50)
    private String typeDocumentJustificatif;

    @Column(name = "dateDepot_justificatif", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateDepotJustificatif;

    @Column(name = "statut", nullable = false)
    @Enumerated(EnumType.STRING)
    private Statut statut;
}