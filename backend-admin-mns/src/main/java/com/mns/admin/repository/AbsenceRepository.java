package com.mns.admin.repository;

import com.mns.admin.model.Absence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AbsenceRepository extends JpaRepository<Absence, Long> {
    List<Absence> findByStagiaireIdUtilisateur(Long stagiaireId);
}
