package com.mns.admin.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roleModule")
public class RoleModule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_roleModule")
    private Long idRoleModule;

    @ManyToOne
    @JoinColumn(name = "id_module", nullable = false)
    private Module module;

    @Column(name = "actif_roleModule", nullable = false)
    private boolean actifRoleModule;
}