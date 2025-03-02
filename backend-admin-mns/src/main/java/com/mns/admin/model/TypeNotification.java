package com.mns.admin.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "type_notification")
public class TypeNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type_notification")
    private Long idTypeNotification;

    @Column(name = "nom_type_notification", nullable = false, length = 50)
    private String nomTypeNotification;
}