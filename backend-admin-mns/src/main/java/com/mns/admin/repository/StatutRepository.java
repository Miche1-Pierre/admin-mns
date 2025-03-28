package com.mns.admin.repository;


import com.mns.admin.model.Statut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatutRepository extends JpaRepository<Statut, Long> {
    Optional<Statut> findByStatutEnum(Statut.StatutEnum statutEnum);
}