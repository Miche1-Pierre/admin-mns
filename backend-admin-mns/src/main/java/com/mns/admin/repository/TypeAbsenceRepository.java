package com.mns.admin.repository;

import com.mns.admin.model.TypeAbsence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeAbsenceRepository extends JpaRepository<TypeAbsence, Long> {
}
