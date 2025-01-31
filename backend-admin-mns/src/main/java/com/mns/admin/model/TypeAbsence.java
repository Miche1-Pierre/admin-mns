package com.mns.admin.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "typeAbsence")
public class TypeAbsence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_typeAbsence")
    private Long idTypeAbsence;

    @Column(name = "nom_typeAbsence", nullable = false, length = 50)
    private String nomTypeAbsence;
}