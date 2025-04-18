package com.mns.admin.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "type_document")
public class TypeDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type_document")
    private Long idTypeDocument;

    @Column(name = "nom_type_document", nullable = false, length = 50)
    private String nomTypeDocument;

    @Column(name = "description_type_document")
    private String descriptionTypeDocument;

    @Column(name = "chiffrement_requis_type_document", nullable = false)
    private boolean chiffrementRequisTypeDocument;

    @Column(name = "obligatoire_type_document", nullable = false)
    private boolean obligatoireTypeDocument;

    public Long getIdTypeDocument() {
        return idTypeDocument;
    }

    public void setIdTypeDocument(Long idTypeDocument) {
        this.idTypeDocument = idTypeDocument;
    }

    public String getNomTypeDocument() {
        return nomTypeDocument;
    }

    public void setNomTypeDocument(String nomTypeDocument) {
        this.nomTypeDocument = nomTypeDocument;
    }

    public String getDescriptionTypeDocument() {
        return descriptionTypeDocument;
    }

    public void setDescriptionTypeDocument(String descriptionTypeDocument) {
        this.descriptionTypeDocument = descriptionTypeDocument;
    }

    public boolean isChiffrementRequisTypeDocument() {
        return chiffrementRequisTypeDocument;
    }

    public void setChiffrementRequisTypeDocument(boolean chiffrementRequisTypeDocument) {
        this.chiffrementRequisTypeDocument = chiffrementRequisTypeDocument;
    }

    public boolean isObligatoireTypeDocument() {
        return obligatoireTypeDocument;
    }

    public void setObligatoireTypeDocument(boolean obligatoireTypeDocument) {
        this.obligatoireTypeDocument = obligatoireTypeDocument;
    }
}