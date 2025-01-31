package com.mns.admin.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "typeDocument")
public class TypeDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_typeDocument")
    private Long idTypeDocument;

    @Column(name = "nom_typeDocument", nullable = false, length = 50)
    private String nomTypeDocument;

    @Column(name = "description_typeDocument")
    private String descriptionTypeDocument;

    @Column(name = "chiffrementRequis_typeDocument", nullable = false)
    private boolean chiffrementRequisTypeDocument;

    @Column(name = "obligatoire_typeDocument", nullable = false)
    private boolean obligatoireTypeDocument;
}