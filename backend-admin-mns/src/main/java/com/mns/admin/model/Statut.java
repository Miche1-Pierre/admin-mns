package com.mns.admin.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Statut {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStatut;

    @Column(name = "statut", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatutEnum statutEnum;

    // Enumération des statuts
    public enum StatutEnum {
        EN_ATTENTE("En attente"),
        ACCEPTE("Accepté"),
        REFUSE("Refusé");

        private final String statutName;

        StatutEnum(String statutName) {
            this.statutName = statutName;
        }

        public String getStatutName() {
            return statutName;
        }
    }

    public Long getIdStatut() {
        return idStatut;
    }

    public void setIdStatut(Long idStatut) {
        this.idStatut = idStatut;
    }

    public StatutEnum getStatutEnum() {
        return statutEnum;
    }

    public void setStatutEnum(StatutEnum statutEnum) {
        this.statutEnum = statutEnum;
    }
}
