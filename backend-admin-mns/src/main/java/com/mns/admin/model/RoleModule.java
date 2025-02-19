package com.mns.admin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "role_module")
public class RoleModule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role_module")
    private Long idRoleModule;

    @ManyToOne
    @JoinColumn(name = "id_role", nullable = false)
    @JsonIgnore
    private Role role;

    @ManyToOne
    @JoinColumn(name = "id_module", nullable = false)
    @JsonIgnore
    private Module module;

    @Column(name = "actif_role_module", nullable = false)
    private boolean actifRoleModule;
}