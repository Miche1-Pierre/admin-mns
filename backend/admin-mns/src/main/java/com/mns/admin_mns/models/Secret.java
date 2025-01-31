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
@Table(name = "secret")
public class Secret {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_secret")
    private Long idSecret;

    @Column(name = "nom_secret", nullable = false, length = 100)
    private String nomSecret;

    @Column(name ="description_secret")
    private String descriptionSecret;

    @Column(name = "dateCreation_secret", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreationSecret;

    @Column(name = "statut_secret", nullable = false)
    private String statutSecret;

    @Column(name = "completeHash_secret", nullable = false)
    private String completeHashSecret;
}
