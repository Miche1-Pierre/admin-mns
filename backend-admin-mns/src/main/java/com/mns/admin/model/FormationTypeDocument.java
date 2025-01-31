package com.mns.admin.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "formationTypeDocument")
public class FormationTypeDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_formationTypeDocument")
    private Long idFormationTypeDocument;

    @ManyToOne
    @JoinColumn(name = "id_typeDocument", nullable = false)
    private TypeDocument typeDocument;

    @ManyToOne
    @JoinColumn(name = "id_formation", nullable = false)
    private Formation formation;

    @Column(name = "obligatoire_formationTypeDocument")
    private boolean obligatoire_formationTypeDocument;
}