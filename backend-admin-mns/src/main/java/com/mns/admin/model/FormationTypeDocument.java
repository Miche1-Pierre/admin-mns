package com.mns.admin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "formation_type_document")
public class FormationTypeDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_formation_type_document")
    private Long idFormationTypeDocument;

    @ManyToOne
    @JoinColumn(name = "id_type_document", nullable = false)
    @JsonIgnore
    private TypeDocument typeDocument;

    @ManyToOne
    @JoinColumn(name = "id_formation", nullable = false)
    @JsonIgnore
    private Formation formation;

    @Column(name = "obligatoire_formation_type_document")
    private boolean obligatoire_formationTypeDocument;
}