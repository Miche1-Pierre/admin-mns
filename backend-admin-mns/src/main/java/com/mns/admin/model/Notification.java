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
@Table(name = "notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notification")
    private Long idNotification;

    @ManyToOne
    @JoinColumn(name = "id_utilisateur", nullable = false)
    @JsonIgnore
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "id_destinataire", nullable = false)
    @JsonIgnore
    private Utilisateur destinataire;

    @ManyToOne
    @JoinColumn(name = "id_type_notification", nullable = false)
    @JsonIgnore
    private TypeNotification typeNotification;

    @Column(name = "contenu_notification", nullable = false)
    private String contenuNotification;

    @Column(name = "date_notification", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateNotification;

    @Column(name = "statut_notification", nullable = false, length = 50)
    private String statutNotification;
}