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
@Table(name = "partSecret")
public class PartSecret {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_partSecret")
    private Long idPartSecret;

    @ManyToOne
    @JoinColumn(name = "id_secret", nullable = false)
    @JsonIgnore
    private Secret secret;

    @ManyToOne
    @JoinColumn(name = "id_uttilisateur", nullable = false)
    @JsonIgnore
    private Utilisateur uttilisateur;

    @Column(name = "part_partSecret", nullable = false)
    private String partPartSecret;

    @Column(name = "dateCreation_partSecret", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateCreationPartSecret;

    @Column(name = "statut_partSecret", nullable = false, length = 50)
    private String statutPartSecret;
}