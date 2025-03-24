package com.mns.admin.repository;

import com.mns.admin.dto.AbsenceDto;
import com.mns.admin.model.Absence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AbsenceRepository extends JpaRepository<Absence, Long> {
    List<Absence> findByStagiaireIdUtilisateur(Long idStagiaire);

    @Query("SELECT new com.mns.admin.dto.AbsenceDto(a.idAbsence, a.dateDebutAbsence, a.dateFinAbsence, a.statutAbsence, a.justifieAbsence, u.nomUtilisateur, ta.nomTypeAbsence, a.etatAbsence) " +
            "FROM Absence a " +
            "JOIN a.stagiaire u " +
            "JOIN a.typeAbsence ta " +
            "WHERE a.idAbsence = :id")
    Optional<AbsenceDto> findAbsenceDetailsById(Long id);
}
