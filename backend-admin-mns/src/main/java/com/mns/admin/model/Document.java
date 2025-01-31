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
@Table(name = "document")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_document")
    private Long idDocument;

    @ManyToOne
    @JoinColumn(name = "id_dossier", nullable = false)
    private Dossier dossier;

    @ManyToOne
    @JoinColumn(name = "id_typeDocument", nullable = false)
    private TypeDocument typeDocument;

    @ManyToOne
    @JoinColumn(name = "id_statut", nullable = false)
    private Statut statut;

    @ManyToOne
    @JoinColumn(name = "id_secret")
    @JsonIgnore
    private Secret secret;

    @Column(name = "dateDepot_document", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDepotDocument;

    @Column(name = "dateLimite_document")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateLimiteDocument;

    @Column(name = "cleChiffrement_document", nullable = false)
    private String cleChiffrement_document;

    @Column(name = "contenuChiffre_document", nullable = false)
    @Lob
    private byte[] contenuChiffreDocument;
}