package com.mns.admin.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "typeNotification")
public class TypeNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_typeNotification")
    private Long idTypeNotification;

    @Column(name = "nom_typeNotification", nullable = false, length = 50)
    private String nomTypeNotification;
}