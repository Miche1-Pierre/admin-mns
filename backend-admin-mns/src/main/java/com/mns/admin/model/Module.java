package com.mns.admin.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "module")
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_module")
    private Long idModule;

    @Column(name = "nom_module", nullable = false, length = 100)
    private String nomModule;

    @Column(name = "description_module")
    private String descriptionModule;
}