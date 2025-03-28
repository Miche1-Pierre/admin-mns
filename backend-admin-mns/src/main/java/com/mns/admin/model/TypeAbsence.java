package com.mns.admin.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "type_absence")
public class TypeAbsence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type_absence")
    private Long idTypeAbsence;

    @Column(name = "nom_type_absence", nullable = false, length = 50)
    private String nomTypeAbsence;

    public Long getIdTypeAbsence() {
        return idTypeAbsence;
    }

    public void setIdTypeAbsence(Long idTypeAbsence) {
        this.idTypeAbsence = idTypeAbsence;
    }

    public String getNomTypeAbsence() {
        return nomTypeAbsence;
    }

    public void setNomTypeAbsence(String nomTypeAbsence) {
        this.nomTypeAbsence = nomTypeAbsence;
    }
}