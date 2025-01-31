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
@Table(name = "inscription", uniqueConstraints = @UniqueConstraint(columnNames = {"id_stagiaire", "id_formation"}))
public class Inscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inscription")
    private Long idInscription;

    @ManyToOne
    @JoinColumn(name = "id_stagiaire", nullable = false)
    @JsonIgnore
    private Utilisateur stagiaire;

    @ManyToOne
    @JoinColumn(name = "id_formation", nullable = false)
    @JsonIgnore
    private Formation formation;

    @Column(name = "date_inscription", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateInscription;
}