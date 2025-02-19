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
@Table(name = "part_secret")
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
    @JoinColumn(name = "id_utilisateur", nullable = false)
    @JsonIgnore
    private Utilisateur utilisateur;

    @Column(name = "part_part_secret", nullable = false)
    private String partPartSecret;

    @Column(name = "date_creation_part_secret", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateCreationPartSecret;

    @Column(name = "statut_part_secret", nullable = false, length = 50)
    private String statutPartSecret;
}