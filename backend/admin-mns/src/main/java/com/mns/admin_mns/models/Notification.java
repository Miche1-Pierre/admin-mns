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
@Table(name = "notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notification")
    private Long idNotification;

    @ManyToOne
    @JoinColumn(name = "id_utilisateur", nullable = false)
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "id_typeNotification", nullable = false)
    private TypeNotification typeNotification;

    @Column(name = "contenu_notification", nullable = false)
    private String contenuNotification;

    @Column(name = "date_notification", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateNotification;

    @Column(name = "statut_notification", nullable = false, length = 50)
    private String statutNotification;
}
